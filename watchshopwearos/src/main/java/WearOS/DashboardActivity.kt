package WearOS

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import com.example.watchshopwearos.R

class DashboardActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Enables Always-on
        setAmbientEnabled()
    }
}