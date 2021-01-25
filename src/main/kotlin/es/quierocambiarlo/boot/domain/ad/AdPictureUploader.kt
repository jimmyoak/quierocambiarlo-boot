package es.quierocambiarlo.boot.domain.ad

import org.springframework.http.codec.multipart.FilePart

interface AdPictureUploader {
    suspend fun upload(file: FilePart): AdPicture
}
