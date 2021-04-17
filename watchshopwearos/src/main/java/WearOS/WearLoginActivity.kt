package WearOS

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class WearLoginActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wear_login)

        // Enables Always-on
        setAmbientEnabled()
    }
}