package com.example.learningpackageeditor.ui.navHost


sealed class Screens(val route: String, val title: String) {

    object LoginScreen : Screens("Login", "Login")
    object signUpScreen : Screens("SignUp", "Sign Up")
    object PackageScreen : Screens("Complete", "Packages")
    object AddWordScreen: Screens("EditAddWord", "Add word")
    object EditAddPackageScreen: Screens("EditAddPackage", "Edit Add Package")
    object ResourceEditor: Screens("ResourcesScreen", "Edit Add Resource")
}
