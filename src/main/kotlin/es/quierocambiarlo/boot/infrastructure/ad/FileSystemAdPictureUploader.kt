package es.quierocambiarlo.boot.infrastructure.ad

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.JpegWriter
import es.quierocambiarlo.boot.domain.ad.AdPicture
import es.quierocambiarlo.boot.domain.ad.AdPictureUploader
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import java.io.File
import java.io.InputStream
import java.io.SequenceInputStream
import java.util.UUID
import javax.validation.ValidationException

private const val MAX_SIZE = 1920

@Service
class FileSystemAdPictureUploader(
    @Value("\${application.ads.pictures.path}") private val path: File
) : AdPictureUploader {
    init {
        if (!path.isDirectory) throw ValidationException("Path must be a directory")
    }

    private val imageLoader = ImmutableImage.loader().detectOrientation(true)

    override suspend fun upload(file: FilePart): AdPicture = coroutineScope {
        val content = readContent(file)
        val resized = resizePicture(content)

        resized.use {
            val destination = File(path, "${UUID.randomUUID()}.jpg")
            destination.writeBytes(it.readBytes())
            AdPicture(destination.name)
        }
    }

    private fun resizePicture(content: InputStream) =
        imageLoader.fromStream(content)
            .bound(MAX_SIZE, MAX_SIZE)
            .bytes(JpegWriter(75, true))
            .inputStream()

    private suspend fun readContent(file: FilePart): InputStream =
        file.content()
            .map(DataBuffer::asInputStream)
            .reduce(::SequenceInputStream)
            .awaitSingle()
}
