package pl.org.seva.multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform