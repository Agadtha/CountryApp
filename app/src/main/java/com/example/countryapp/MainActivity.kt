package com.example.countryapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker



import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.countryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>

    private val countries = arrayOf(
        "Indonesia",
        "United States",
        "United Kingdom",
        "Germany",
        "France",
        "Australia",
        "Japan",
        "China",
        "Brazil",
        "Canada"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ambil array dari strings.xml
        provinces = resources.getStringArray(R.array.provinces)

        // --- DatePicker widget ---
        val datePicker = binding.datePicker
        datePicker.init(
            datePicker.year,
            datePicker.month,
            datePicker.dayOfMonth
        ) { _, year, monthOfYear, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
        }

        // --- Spinner country ---
        val countryAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            countries
        )
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCountry.adapter = countryAdapter

        // --- Spinner province ---
        val provinceAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            provinces
        )
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProvinces.adapter = provinceAdapter

        binding.spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, countries[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // --- TimePicker ---
        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            Toast.makeText(this, selectedTime, Toast.LENGTH_SHORT).show()
        }

        // --- Button untuk show DatePickerDialog ---
        binding.btnShowCalendar.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                this,
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            )
            datePickerDialog.show()
        }



    }

    override fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        Toast.makeText(this, "Dipilih: $selectedDate", Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val selectedTime = String.format("%02d:%02d", p1, p2)
        Toast.makeText(this@MainActivity, selectedTime,
            Toast.LENGTH_SHORT).show()
    }
}
