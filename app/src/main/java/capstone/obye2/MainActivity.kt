package capstone.obye2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import capstone.obye2.model.RequestData
import capstone.obye2.model.ResponseData
import capstone.obye2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGender = findViewById<RadioGroup>(R.id.radioGender)
        val radioFamilyHistory = findViewById<RadioGroup>(R.id.radioFamilyHistory)
        val inputAge = findViewById<EditText>(R.id.inputAge)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnSubmit.setOnClickListener {
            val gender = if (radioGender.checkedRadioButtonId == R.id.radioFemale) 0 else 1
            val familyHistory =
                if (radioFamilyHistory.checkedRadioButtonId == R.id.radioNoFamilyHistory) 0 else 1

            val age = inputAge.text.toString().toIntOrNull()
            if (age == null) {
                Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val requestData = RequestData(
                age = age,
                height = 1.75, // Ganti sesuai kebutuhan
                weight = 70.0, // Ganti sesuai kebutuhan
                gender = gender,
                family_history = familyHistory
            )

            RetrofitClient.instance.submitData(requestData).enqueue(object : Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    if (response.isSuccessful) {
                        // Extract and display the result
                        val predictedClass = response.body()?.predicted_class ?: "No Result Found"
                        tvResult.text = "Predicted Class: $predictedClass"
                    } else {
                        tvResult.text = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
