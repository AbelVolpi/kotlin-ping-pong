import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main() {
    // args dão o conteúdo da mensagem e o nome do servidor
    var aSocket: DatagramSocket? = null
    try {
        aSocket = DatagramSocket()
        val msg = "Hello"
        val m = msg.toByteArray()
        val aHost: InetAddress = InetAddress.getByName("localhost")
        val serverPort = 6789
        val request = DatagramPacket(
            m, msg.length,
            aHost, serverPort
        )

        // Envie uma mensagem
        aSocket.send(request)

        // Prepare uma mensagem vazia para uma resposta
        val buffer = ByteArray(1000)
        val response = DatagramPacket(buffer, buffer.size)

        // Receba a resposta
        aSocket.receive(response)

        // Imprima a resposta
        val resp = String(response.data)
        println("Client received: $resp")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        aSocket?.close()
    }
}