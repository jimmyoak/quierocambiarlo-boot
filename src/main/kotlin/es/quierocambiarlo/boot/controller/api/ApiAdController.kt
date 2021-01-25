package es.quierocambiarlo.boot.controller.api

import es.quierocambiarlo.boot.domain.ad.Ad
import es.quierocambiarlo.boot.domain.ad.AdPicture
import es.quierocambiarlo.boot.domain.ad.AdPictureUploader
import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.CategoryId
import es.quierocambiarlo.boot.domain.location.Province
import es.quierocambiarlo.boot.domain.user.User
import es.quierocambiarlo.boot.domain.user.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@RestController
class ApiAdController(
    private val adRepository: AdRepository,
    private val userRepository: UserRepository,
    private val adPictureUploader: AdPictureUploader
) {
    @PostMapping("/api/v1/ads", produces = [MediaType.APPLICATION_JSON_VALUE])
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun publish(@Valid @ModelAttribute formData: PublishAdFormData): Map<String, UUID> = coroutineScope {
        val user = userRepository.findByEmail(formData.advertiser_email) ?: createUserFrom(formData)
        val uploadedPictures = formData.pictures.map { adPictureUploader.upload(it) }
        val ad = createAdFrom(formData, user.id, uploadedPictures)

        val persistUser = async { userRepository.save(user) }
        val persistAd = async { adRepository.save(ad) }

        mapOf("adId" to persistAd.await().id, "userId" to persistUser.await().id)
    }

    private fun createUserFrom(formData: PublishAdFormData): User.NonRegistered =
        User.NonRegistered(
            name = formData.advertiser_name,
            email = formData.advertiser_email,
            phone = formData.advertiser_phone
        )

    private fun createAdFrom(formData: PublishAdFormData, advertiserId: UUID, uploadedPictures: List<AdPicture>): Ad =
        Ad(
            title = formData.title,
            categoryId = formData.category,
            province = formData.province,
            description = formData.description,
            pictures = uploadedPictures,
            interestedOn = formData.interested_on,
            advertiserId = advertiserId
        )
}

class PublishAdFormData(
    @Size(min = 5, max = 100) val title: String,
    val category: CategoryId,
    val province: Province,
    @Size(min = 20, max = 300) val description: String,
    val pictures: List<FilePart> = emptyList(),
    @Size(min = 6, max = 100) val interested_on: String,
    @Size(min = 3, max = 50) val advertiser_name: String,
    @Email val advertiser_email: String,
    @Size(min = 9, max = 12) val advertiser_phone: String?
)

