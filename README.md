# AppVersionFromGooglePlay

Get the latest app version number from Google Play, support the latest version of googleplay in 2022

# How to Use

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
implementation 'com.github.sunheihei:AppVersionFromGooglePlay:v1.0.1'
```

## KOTLIN:

```
 val packName = "com.facebook.orca"
 AppVersionOnGooglePlayUtils(this, packName).getVersionOnGooglePlay {
                Log.d(TAG, "version is $it")
                 //version is 370.0.0.14.108
        }
```
