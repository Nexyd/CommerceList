package dani.kotlin.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dani.kotlin.app.R
import dani.kotlin.app.ui.adapter.CommerceAdapter
import dani.kotlin.domain.entities.CommerceInfo
import dani.kotlin.app.CommerceViewModel
import dani.kotlin.app.databinding.ActivityMainBinding
import dani.kotlin.app.framework.CommerceAPI
import dani.kotlin.app.framework.GeoLocator
import dani.kotlin.app.framework.PermissionChecker
import dani.kotlin.app.ui.presenter.MainPresenter
import dani.kotlin.app.ui.listener.DialogListener
import dani.kotlin.data.repository.CommerceRepository
import dani.kotlin.data.repository.LocationRepository
import dani.kotlin.data.repository.PermissionRepository
import dani.kotlin.domain.CommerceOperations
import dani.kotlin.interactors.CommerceInteractor
import dani.kotlin.interactors.LocationInteractor
import dani.kotlin.interactors.PermissionInteractor

class MainActivity : AppCompatActivity(), DialogListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: CommerceViewModel
    private lateinit var commerceAdapter: CommerceAdapter
    private val presenter: MainPresenter
    private val commerces = ArrayList<CommerceInfo>()
    private var filters = ArrayList<String>()

    init {
        val permissionChecker = PermissionChecker(this)
        val permissionRepository = PermissionRepository(permissionChecker)

        val commerceApi = CommerceAPI()
        val commerceOperations = CommerceOperations()
        val commerceRepository = CommerceRepository(
            commerceApi, commerceOperations)

        val geoLocator = GeoLocator(this)
        val geoRepository = LocationRepository(geoLocator)

        presenter = MainPresenter(
            PermissionInteractor(permissionRepository),
            CommerceInteractor(commerceRepository),
            LocationInteractor(geoRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        presenter.onCreate()
        model = ViewModelProvider(this)
            .get(CommerceViewModel::class.java)

        model.init(this)
        binding.viewModel = model
        binding.lifecycleOwner = this

        commerceAdapter = CommerceAdapter(commerces)
        binding.commerceList.apply {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = commerceAdapter
        }

        CommerceViewModel.commerces.observe(this, {
            this.commerces.clear()
            this.commerces.addAll(it)

            runOnUiThread { commerceAdapter.notifyDataSetChanged() }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter_by -> { presenter.showDialog(supportFragmentManager, filters); true }
        R.id.sort_proximity -> { presenter.sortByProximity(commerces); true }
        else -> { super.onOptionsItemSelected(item) }
    }

    override fun onDialogClosed(filters: ArrayList<String>) {
        this.filters = filters
        presenter.filterCommerces(filters)
    }
}