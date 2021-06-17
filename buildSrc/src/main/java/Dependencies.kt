object AppDefine {
    const val applicationId = "com.vib.app"
    const val compileSdk = 30
    const val buildToolsVersion = "30.0.3"
    const val minSdk = 23
    const val targetSdk = 30
}

object Releases {
    const val versionCode = 1000
    const val versionName = "1.0.0"
}

object Versions {
    const val kotlin = "1.4.21"
    const val gradle = "4.1.1"
    const val googleService = "4.3.4"
    const val realm = "10.4.0"
    const val espressoPlugin = "3.0.2"
    const val firebaseGradle = "2.3.0"
    const val googleMap = "17.0.0"
    const val androidAppCompat = "27.1.1"
    const val androidXAppCompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintLayout = "2.0.4"
    const val coordinateLayout = "1.1.0"
    const val cardViewVersion = "1.0.0"
    const val recyclerview = "1.1.0"
    const val support = "28.0.0"
    const val activityVersion = "1.1.0"
    const val navigation = "2.4.0-alpha01"
    const val navVersion = "2.3.2"
    const val annotation = "1.1.0"
    const val gson = "2.8.5"
    const val okHttp = "4.8.1"
    const val coroutines = "1.3.4"
    const val coroutinesCommon = "1.3.7"
    const val retrofit = "2.9.0"
    const val retrofitGson = "2.9.0"
    const val retrofitAdapter = "2.9.0"
    const val dagger2 = "2.28.3"
    const val rxJava = "2.2.4"
    const val rxNet = "1.0.1"
    const val rxAndroid = "2.1.1"
    const val rxPermission = "0.10.2"
    const val picasso = "2.71828"
    const val glide = "4.9.0"
    const val fragmentManager = "1.0.1"
    const val fmEventScope = "1.1.0"
    const val androidLegacy = "1.0.0"
    const val smartTab = "2.0.0@aar"
    const val multiStateView = "1.3.2"
    const val eventBus = "3.2.0"
    const val dimension = "1.0.6"
    const val multidex = "2.0.1"
    const val materialDialogDesign = "3.1.1"
    const val materialDesign = "1.2.1"
    const val skeletonVersion = "2.1.0"
    const val lifecycleCompiler = "2.0.0"
    const val lifecycleKTXVersion = "2.3.0-alpha04"
    const val lifecycleExtensionsVersion = "2.2.0"
    const val databinding = "4.1.1"
    const val googleAuth = "17.0.0"
    const val fireBaseCore = "17.2.0"
    const val fireBaseMessage = "20.0.0"
    const val fireBaseAnalytics = "17.5.0"
    const val fireBaseCrashlytics = "17.2.2"
    const val fireBaseDynamic = "19.0.0"
    const val fireBaseConfig = "19.0.3"
    const val fireBasePerf = "19.0.2"
    const val fireBaseAuth = "19.1.0"
    const val googleZXing = "3.3.3"
    const val zXing = "4.2.0"
    const val swipeLayout = "1.2.0@aar"


    //Test
    const val unitTest = "4.13.2"
    const val androidTestCore = "1.1.0"
    const val androidTestRunner = "1.6.2"
    const val androidJunit = "1.1.2"
    const val testRulesVersion = "1.3.0"
    const val espressoVersion = "3.3.0"
    const val pdfView = "2.8.2"
    const val biometric = "1.2.0-alpha03"


}

object ClassPath {
    const val buildRToolsGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleService}"
    const val realmPlugin = "io.realm:realm-gradle-plugin:${Versions.realm}"
    const val espresso =
        "com.android.support.test.espresso:espresso-idling-resource:${Versions.espressoPlugin}"
    const val firebaseGradle =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseGradle}"
}

object AARLibraries {
    const val typeAAR = "aar"
    const val viewpagerIndicator = "viewpagerindicator-2.4.1"
    const val qrScanSDK = "qr-scan-sdk-release-1.0.0"
    const val ticketSDK = "Ticketlib-release"
    const val wheelLib = ":androidWheelLibrary"
    const val simpleCropImgLib = ":simplecropimagelib"
    const val advancedRecycleView = ":advancedRecyclerview"
}

object Libraries {
    //RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitAdapter}"
    /*const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val retrofitConverterMoshi =
        "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"*/

    //DAGGER2
    const val dagger = "com.google.dagger:dagger:${Versions.dagger2}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger2}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger2}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger2}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger2}"

    //RXJAVA 2
    const val rxNetworking = "com.amitshekhar.android:rx2-android-networking:${Versions.rxNet}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val eventBus = "org.greenrobot:eventbus:${Versions.eventBus}"

    //FRAGMENT MANAGER
    const val fragmentManager = "me.yokeyword:fragmentationx:${Versions.fragmentManager}"
    const val fmEventScope = "me.yokeyword:eventbus-activity-scope:${Versions.fmEventScope}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val zXing = "com.journeyapps:zxing-android-embedded:${Versions.zXing}"
    const val googleZXing = "com.google.zxing:core:${Versions.googleZXing}"
    const val pdfView = "com.github.barteksc:android-pdf-viewer:${Versions.pdfView}"
    const val biometric = "androidx.biometric:biometric:${Versions.biometric}"

}

object KotlinLibraries {
    // KOTLIN
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinCoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinCoroutineCoreCommon =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutinesCommon}"
}

object AndroidLibraries {
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    // ANDROID
    const val androidAppCompat = "com.android.support:appcompat-v7:${Versions.androidAppCompat}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
    const val androidXAppCompat = "androidx.appcompat:appcompat:${Versions.androidXAppCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val annotationX = "androidx.annotation:annotation:${Versions.annotation}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coordinateLayout =
        "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinateLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val androidSupportDesign = "com.android.support:design:${Versions.support}"
    const val androidXActivityJava = "androidx.activity:activity:${Versions.activityVersion}"
    const val androidXActivityKotlin = "androidx.activity:activity:${Versions.activityVersion}"

    // DATA BINDING
    const val databinding = "androidx.databinding:databinding-compiler:${Versions.databinding}"
    const val lifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKTXVersion}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKTXVersion}"
    const val lifecycleCompiler =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleCompiler}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensionsVersion}"

    //https://developer.android.com/jetpack/androidx/releases/navigation
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

object UILibraries {
    //Material Design
    const val material = "com.google.android.material:material:${Versions.materialDesign}"

    //MATERIAL DIALOG DESIGN
    const val meterialLifecyle =
        "com.afollestad.material-dialogs:lifecycle:${Versions.materialDialogDesign}"
    const val materialDialog =
        "com.afollestad.material-dialogs:core:${Versions.materialDialogDesign}"
    const val materialBottomDialog =
        "com.afollestad.material-dialogs:bottomsheets:${Versions.materialDialogDesign}"

    //SMART TAB
    const val smartTab = "com.ogaclejapan.smarttablayout:library:${Versions.smartTab}"
    const val smartTabUtils = "com.ogaclejapan.smarttablayout:utils-v4:${Versions.smartTab}"

    //MULTISTATE VIEW
    const val multiStateView = "com.github.Kennyc1012:MultiStateView:${Versions.multiStateView}"

    //DIMENSION
    const val dimensionSDP = "com.intuit.sdp:sdp-android:${Versions.dimension}"
    const val dimensionSSP = "com.intuit.ssp:ssp-android:${Versions.dimension}"

    //SKELETON LAYOUT
    const val skeletonLayout = "io.supercharge:shimmerlayout:${Versions.skeletonVersion}"

    //SWIPE ITEM
    const val swipeLayout = "com.daimajia.swipelayout:library:${Versions.swipeLayout}"
}

object GoogleLibraries {
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    const val googleMaps = "com.google.android.gms:play-services-maps:${Versions.googleMap}"
    const val firebaseConfig = "com.google.firebase:firebase-config:${Versions.fireBaseConfig}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Versions.fireBaseCore}"
    const val firebaseDynamic =
        "com.google.firebase:firebase-dynamic-links:${Versions.fireBaseDynamic}"
    const val firebaseMessage = "com.google.firebase:firebase-messaging:${Versions.fireBaseMessage}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.fireBaseAuth}"
    const val firebaseAnalytics =
        "com.google.firebase:firebase-analytics:${Versions.fireBaseAnalytics}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.fireBaseCrashlytics}"
    const val firebasePerformance = "com.google.firebase:firebase-perf:${Versions.fireBasePerf}"
}

object TestLibraries {
    // ANDROID TEST
    const val unitTest = "junit:junit:${Versions.unitTest}"
    const val androidTestCore = "androidx.test:core:${Versions.androidTestCore}"
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val androidTextRule = "androidx.test:rules:${Versions.testRulesVersion}"
    const val junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val testEspressoIntent =
        "androidx.test.espresso:espresso-intents:${Versions.espressoVersion}"
    const val testEspressoIdling =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espressoVersion}"
    const val testEspressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoVersion}"
}