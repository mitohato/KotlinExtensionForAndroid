//UI
//UIスレッドで実行
fun mainThread(runnable: () -> Unit) {
  uiHandler.post(runnable)}

//短いトースト
fun Context.toast(message: String) {
  mainThread { Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}}

//長いトースト
fun Context.longToast(message: String) {
  mainThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show()}}

//intent
//インテントを作成
inline fun <reified T : Context> Context.newIntent(): Intent = Intent(this, T::class.java)

//Bundle付き
inline fun <reified T : Context> Context.newIntent(extras: Bundle): Intent = newIntent<T>(0, extras)

//インテントを定義してアクティビティを起動
inline fun <reified T : Activity> Activity.startActivity(): Unit =this.startActivity(newIntent<T>())


//SystemService
fun Context.getActivityManager(): ActivityManager =
        getSystemServiceAs(Context.ACTIVITY_SERVICE)

fun Context.getClipboardManager(): ClipboardManager =
        getSystemServiceAs(Context.CLIPBOARD_SERVICE)

fun Context.getCameraManager(): CameraManager =
        getSystemServiceAs(Context.CAMERA_SERVICE)

fun Context.getNotificationManager(): NotificationManager =
        getSystemServiceAs(Context.NOTIFICATION_SERVICE)

@Suppress("UNCHECKED_CAST")
fun <T> Context.getSystemServiceAs(serviceName: String): T =
        this.getSystemService(serviceName) as T

//Judge API
private val version: Int
    get() = Build.VERSION.SDK_INT

fun fromApi(fromVersion: Int, inclusive: Boolean = true, action: () -> Unit) {
    if (version > fromVersion || (inclusive && version == fromVersion)) action()
  }
