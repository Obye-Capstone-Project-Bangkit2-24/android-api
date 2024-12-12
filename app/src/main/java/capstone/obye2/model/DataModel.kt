package capstone.obye2.model

data class RequestData(
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: Int,
    val family_history: Int,
    val high_caloric_food: Int = 0,
    val freq_vegetables: Int = 3,
    val main_meals: Int = 3,
    val food_between_meals: Int = 1,
    val smoking: Int = 0,
    val water_daily: Int = 2,
    val calories_monitor: Int = 1,
    val physical_activity_freq: Int = 3,
    val time_using_devices: Int = 2,
    val alcohol_consumption: Int = 1,
    val transportation: Int = 4,
    val obesity: Int = 1
)

data class ResponseData(
    val predicted_class: String // Nama field harus sesuai dengan API
)
