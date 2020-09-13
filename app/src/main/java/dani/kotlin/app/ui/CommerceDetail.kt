package dani.kotlin.app.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dani.kotlin.app.R
import dani.kotlin.domain.entities.CommerceInfo
import dani.kotlin.domain.entities.ImagesInfo
import dani.kotlin.app.CommerceViewModel
import dani.kotlin.app.databinding.CommerceDetailBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class CommerceDetail : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: CommerceDetailBinding
    private val data = CommerceViewModel.detailCommerce

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CommerceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (::map.isInitialized) map.clear()
        val mapFragment = supportFragmentManager.
            findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
        data.observe(this, {
            setDetails(it)
            setSocialVisibility(it)

            if (it.photos != null && it.photos!!.isNotEmpty())
                setupCarousel(it)
            else if (it.logo != null)
                setLogo(it)
            else
                binding.carousel.visibility = View.GONE
        })
    }

    private fun setDetails(data: CommerceInfo) {
        binding.detailName.text = formatText(R.string.detail_name, data.name)
        binding.detailDescription .text = formatText(R.string.detail_description, data.description)
        binding.detailOpeningHours.text = formatText(
            R.string.detail_opening_hours,
            data.openingHours
        )
        binding.detailPhone.text = formatText(R.string.detail_phone, data.contact?.phone)
        binding.detailEmail.text = formatText(R.string.detail_email, data.contact?.email)
        binding.detailLocation.text = formatText(
            R.string.detail_address,
            "${data.address?.street}, ${data.address?.city}"
        )
    }

    private fun setSocialVisibility(data: CommerceInfo) {
        if (data.social?.twitter != null) {
            binding.detailTwitter.visibility = View.VISIBLE
            binding.detailTwitter.text = formatText(
                R.string.detail_twitter, data.social!!.twitter
            )
        }

        if (data.social?.instagram != null) {
            binding.detailInstagram.visibility = View.VISIBLE
            binding.detailInstagram.text = formatText(
                R.string.detail_instagram, data.social!!.instagram
            )
        }

        if (data.social?.facebook != null) {
            binding.detailFacebook.visibility = View.VISIBLE
            binding.detailFacebook.text = formatText(
                R.string.detail_facebook, data.social!!.facebook
            )
        }

        if (data.social?.tripadvisor != null) {
            binding.detailTripadvisor.visibility = View.VISIBLE
            binding.detailTripadvisor.text = formatText(
                R.string.detail_tripadvisor, data.social!!.tripadvisor
            )
        }
    }

    private fun setupCarousel(data: CommerceInfo) {
        val list = mutableListOf<CarouselItem>()
        for (photo: ImagesInfo in data.photos!!)
            if (photo.url != null) {
                list.add(CarouselItem(
                    photo.url!!, ""))
            }

        binding.carousel.addData(list)
    }

    private fun setLogo(data: CommerceInfo) {
        binding.detailLogo.visibility = View.VISIBLE
        binding.carousel.visibility = View.GONE

        Picasso.get().load(
            data.logo?.thumbnails?.large
        ).into(binding.detailLogo)

        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        params.topToBottom = R.id.detailLogo
        binding.detailName.layoutParams = params
    }

    private fun formatText(res: Int, text: String?): Spanned {
        return if (Build.VERSION.SDK_INT >= 24)
            Html.fromHtml(
                getString(res, text),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        else
            Html.fromHtml(getString(res, text))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true

        val location = LatLng(
            data.value?.latitude!!,
            data.value?.longitude!!
        )

        val markerOptions = MarkerOptions()
            .position(location)

        map.addMarker(markerOptions)
        map.animateCamera(CameraUpdateFactory
            .newLatLngZoom(location, 18f))
    }
}