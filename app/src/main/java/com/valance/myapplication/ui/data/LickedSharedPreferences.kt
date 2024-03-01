import android.content.Context
import android.content.SharedPreferences

class LickedSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("liked_prefs", Context.MODE_PRIVATE)



    // Сохранение списка ID лайкнутых кофе
    fun saveLikedCoffeeIds(likedCoffeeIds: Set<String>) {
        sharedPreferences.edit().putStringSet("liked_coffee_ids", likedCoffeeIds).apply()
    }

    // Получение списка ID лайкнутых кофе
    fun getLikedCoffeeIds(): Set<String> {
        return sharedPreferences.getStringSet("liked_coffee_ids", setOf()) ?: setOf()
    }

    // Проверка, лайкнут ли кофе с определенным ID
    fun isLiked(itemId: String): Boolean {
        return getLikedCoffeeIds().contains(itemId)
    }

    // Установка лайка для кофе с определенным ID
    fun setLiked(itemId: String, liked: Boolean) {
        val likedCoffeeIds = getLikedCoffeeIds().toMutableSet()

        if (liked) {
            likedCoffeeIds.add(itemId)
        } else {
            likedCoffeeIds.remove(itemId)
        }

        saveLikedCoffeeIds(likedCoffeeIds)
    }
}
