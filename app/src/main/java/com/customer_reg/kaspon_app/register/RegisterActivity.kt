package com.customer_reg.kaspon_app.register

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.customer_reg.kaspon_app.R
import com.customer_reg.kaspon_app.databinding.ActivityRegistrationBinding
import com.customer_reg.kaspon_app.network.model.*
import com.customer_reg.kaspon_app.session.PreferenceHelper
import com.customer_reg.kaspon_app.session.setValue

class RegisterActivity : AppCompatActivity() {

    lateinit var registrationBinding: ActivityRegistrationBinding
    lateinit var viewModel: RegisterViewModel
    var country_id=0
    var state_id=0
    var city_id=0
    var amc_id=0
    var product_id=0
    var sub_product_id=0
    var location_id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        registrationBinding.registerViewModel = viewModel


        viewModel.amcResponse.observe(this, Observer {
            val adapter = ArrayAdapter<AmcResponse.AmcDetails>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spAmcType.setAdapter(adapter)
        })
        viewModel.productResponse.observe(this, Observer {
            val adapter = ArrayAdapter<ProductResponse.ProductDetails>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spProduct.setAdapter(adapter)
        })
        viewModel.subProductResponse.observe(this, Observer {
            val adapter = ArrayAdapter<SubProductResponse.SubProduct>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spSubProduct.setAdapter(adapter)
        })
        viewModel.countriesResponse.observe(this, Observer {
            val adapter = ArrayAdapter<CountriesResponse.ResponseData.Country>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spCountry.setAdapter(adapter)
        })

        viewModel.stateResponse.observe(this, Observer {
            val adapter = ArrayAdapter<StatesResponse.State>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spState.setAdapter(adapter)
        })

        viewModel.citiesResponse.observe(this, Observer {
            val adapter = ArrayAdapter<CitiesResponse.City>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spCity.setAdapter(adapter)
        })

        viewModel.locationResponse.observe(this, Observer {
            val adapter = ArrayAdapter<LocationResponse.LocationData>(
                this,
                R.layout.dropdown_menu_popup_item,
                it.response.data
            )
            registrationBinding.spLocation.setAdapter(adapter)
        })


        registrationBinding.spProduct.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            product_id=viewModel.productResponse.value?.response?.data?.get(pos)!!.product_id
            registrationBinding.spSubProduct.setText("")
            viewModel.getSubProduct(viewModel.productResponse.value?.response?.data?.get(pos)!!.product_id)
        }
        registrationBinding.spAmcType.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            amc_id=viewModel.amcResponse.value?.response?.data?.get(pos)!!.id
        }
        registrationBinding.spLocation.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            location_id=viewModel.locationResponse.value?.response?.data?.get(pos)!!.location_id
        }
        registrationBinding.spSubProduct.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            sub_product_id=viewModel.subProductResponse.value?.response?.data?.get(pos)!!.id
        }
        registrationBinding.spCountry.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            country_id=viewModel.countriesResponse.value?.response?.data?.get(pos)!!.country_id
            registrationBinding.spState.setText("")
            viewModel.getStates(viewModel.countriesResponse.value?.response?.data?.get(pos)!!.country_id)
        }
        registrationBinding.spState.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            state_id=viewModel.stateResponse.value?.response?.data?.get(pos)!!.state_id
            registrationBinding.spCity.setText("")
            viewModel.getCities(viewModel.stateResponse.value?.response?.data?.get(pos)!!.state_id)
        }
        registrationBinding.spCity.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, pos: Int, l: Long ->
            registrationBinding.spLocation.setText("")
            city_id=viewModel.citiesResponse.value?.response?.data?.get(pos)!!.city_id
            viewModel.getLocations(viewModel.citiesResponse.value?.response?.data?.get(pos)!!.city_id)
        }

        viewModel.registerResponse.observe(this, Observer {
            Log.d("Token",""+it.token)
            val preferenceHelper= PreferenceHelper(this)
            preferenceHelper.setValue("token",""+it.token)
            Toast.makeText(this, "Enter customer name", Toast.LENGTH_SHORT).show()
        })


        registrationBinding.btnRegister.setOnClickListener{
            if (validation()) {
                var hashMap = HashMap<String, Any>()
                hashMap.put("customer_name", registrationBinding.etCustomerName.text.toString())
                hashMap.put("email_id", registrationBinding.etEmail.text.toString())
                hashMap.put("contact_number", registrationBinding.etMobile.text.toString())
                hashMap.put("alternate_number", registrationBinding.etAlternateNumber.text.toString())
                hashMap.put("product_id", product_id)
                hashMap.put("product_sub_id",sub_product_id)
                hashMap.put("model_no",registrationBinding.etMobile.text.toString())
                hashMap.put("serial_no",registrationBinding.etSerialNumber.text.toString())
                hashMap.put("amc_id",amc_id)
                hashMap.put("contract_duration",registrationBinding.etContractDuration.text.toString())
                hashMap.put("priority","p3")
                hashMap.put("plot_number",registrationBinding.etPlotNumber.text.toString())
                hashMap.put("street",registrationBinding.etStreet.text.toString())
                hashMap.put("post_code",registrationBinding.etPostalCode.text.toString())
                hashMap.put("country_id", country_id)
                hashMap.put("state_id", state_id)
                hashMap.put("city_id", city_id)
                hashMap.put("location_id",location_id)
                hashMap.put("landmark",registrationBinding.etLandmark.text.toString())
                viewModel.registerUser(hashMap)
            }
        }


    }


    private fun validation(): Boolean {

        if (registrationBinding.etCustomerName.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter customer name", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etEmail.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etMobile.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etModelNumber.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter model number", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etSerialNumber.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter serial number", Toast.LENGTH_SHORT).show()
            return false

        } /*else if (registrationBinding.etAlternateNumber.text?.equals("")!!) {
            return false

        }*/ else if (registrationBinding.etContractDuration.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter alternative", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etPlotNumber.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter plot number", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.etStreet.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter street name", Toast.LENGTH_SHORT).show()
            return false

        } /*else if (registrationBinding.etLandmark.text?.equals("")!!) {
            Toast.makeText(this, "Enter landmark", Toast.LENGTH_SHORT).show()
            return false

        } */ else if (registrationBinding.etPostalCode.text?.toString().equals("")!!) {
            Toast.makeText(this, "Enter postal code", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spProduct.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select Product", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spSubProduct.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select sub Product", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spAmcType.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select AMC", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spCountry.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spState.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show()
            return false

        } else if (registrationBinding.spCity.text?.toString().equals("")!!) {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }

    }

}