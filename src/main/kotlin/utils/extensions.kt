package utils

fun String.toValidId() = this.replace("[^a-zA-Z0-9_]".toRegex(), "")
    .replace("[_]+".toRegex(), "_")
    .toLowerCase()
fun String.toValidName():String{
    return this.replace("[^a-zA-Z0-9_ ()]".toRegex(), "")
        .replace("[_]+".toRegex(),"_")
        .replace("[ ]+".toRegex()," ")
        .replace("[(]+".toRegex(),"(")
        .replace("[)]+".toRegex(),")")
}