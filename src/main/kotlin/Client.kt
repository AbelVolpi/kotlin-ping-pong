import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    val serverAddress = "localhost"
    val serverPort = 6789
    while (true) {
        try {
            val socket = DatagramSocket()
            val buffer = ByteArray(1024)
            val pingPacket = DatagramPacket("ping".toByteArray(), 4, InetAddress.getByName(serverAddress), serverPort)
            socket.send(pingPacket)
            println("Client sent: ping")

            val response = DatagramPacket(buffer, buffer.size)
            socket.receive(response)
            val pongMessage = String(response.data, 0, response.length)
            println("Client received: $pongMessage")

            socket.close()
            Thread.sleep(1000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}