import java.net.DatagramPacket
import java.net.DatagramSocket

fun main() {
    var aSocket: DatagramSocket? = null
    try {
        // Crie um socket na porta 6789
        aSocket = DatagramSocket(6789)

        // Prepare uma mensagem vazia
        val buffer = ByteArray(1000)
        val request = DatagramPacket(buffer, buffer.size)

        // Receba uma mensagem
        aSocket.receive(request)

        // Prepare uma resposta
        val messageReceived = String(request.data, 0, request.length)
        val resp = "Server response to $messageReceived"
        val m = resp.toByteArray()

        // Obtenha o endereço/porta de resposta do pedido
        val response = DatagramPacket(m, m.size, request.address, request.port)

        // Envie a resposta
        aSocket.send(response)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        aSocket?.close()
    }
}
