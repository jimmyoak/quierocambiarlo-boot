package es.quierocambiarlo.boot.controller.api

import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.orNotFound
import es.quierocambiarlo.boot.domain.messaging.MessageRepository
import es.quierocambiarlo.boot.domain.user.User
import es.quierocambiarlo.boot.domain.user.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@RestController
class ApiMessagesController(
    private val userRepository: UserRepository,
    private val adRepository: AdRepository,
    private val messageRepository: MessageRepository
) {
    @PostMapping("/api/v1/messages")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun send(
        @Valid @ModelAttribute formData: SendMessageFormData
    ): Unit = coroutineScope {
        val userFetching = async { userRepository.findByEmail(formData.leader_email) ?: createUserFrom(formData) }
        val adFetching = async { adRepository.findById(formData.ad_id).orNotFound() }

        val user = userFetching.await()
        val ad = adFetching.await()

        val offer = user.offersOn(ad, formData.leader_offer)
        messageRepository.save(offer)
    }

    private suspend fun createUserFrom(formData: SendMessageFormData): User.NonRegistered =
        userRepository.save(User.NonRegistered(
            name = formData.leader_name,
            email = formData.leader_email
        ))
}

class SendMessageFormData(
    val ad_id: UUID,
    @Size(min = 3, max = 50) val leader_name: String,
    @Email val leader_email: String,
    @Size(min = 5, max = 100) val leader_offer: String
)
