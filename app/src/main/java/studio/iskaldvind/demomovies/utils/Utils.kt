package studio.iskaldvind.demomovies.utils

private val months = hashMapOf(
    "01" to "Jan",
    "02" to "Feb",
    "03" to "Mar",
    "04" to "Apr",
    "05" to "May",
    "06" to "Jun",
    "07" to "Jul",
    "08" to "Aug",
    "09" to "Sep",
    "10" to "Oct",
    "11" to "Nov",
    "12" to "Dec"
)

fun formatDate(date: String): String {
    val parts = date.split("-")
    return "${parts[2]} ${months[parts[1]]} ${parts[0]}"
}