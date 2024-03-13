import java.net.DatagramPacket
import java.net.DatagramSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main() {
    val serverSocket = DatagramSocket(6789)
    println("Server listening on port 6789")

    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)

    while (true) {
        try {
            val receiveBuffer = ByteArray(1024)
            val receivePacket = DatagramPacket(receiveBuffer, receiveBuffer.size)
            serverSocket.receive(receivePacket)

            val clientAddress = receivePacket.address
            val clientPort = receivePacket.port
            val receivedPingMessage = String(receivePacket.data, 0, receivePacket.length)
            println("Server received from $clientAddress:$clientPort: $receivedPingMessage")

            threadPool.execute {
                Thread.sleep(1000)
                val responseSocket = DatagramSocket()
                val pongPacket = DatagramPacket("pong".toByteArray(), 4, clientAddress, clientPort)
                responseSocket.send(pongPacket)
                println("Server sent to $clientAddress:$clientPort: pong")
                responseSocket.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
