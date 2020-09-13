package dani.kotlin.domain.entities

data class CommerceInfo(
    val address: AddressInfo?,
    val contact: ContactInfo?,
    val social:  SocialInfo?,
    val name: String?,
    val category: String?,
    val shortDescription: String?,
    val description:  String?,
    val openingHours: String?,
    val photos: List<ImagesInfo>?,
    val logo: ImagesInfo?,
    val latitude:  Double?,
    val longitude: Double?
)