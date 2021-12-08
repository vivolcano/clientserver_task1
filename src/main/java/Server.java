import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        // Занимаем порт, определяя серверный сокет
        ServerSocket servSocket = new ServerSocket(23444);

        // Ждем подключения клиента и получаем потоки для дальнейшей работы
        try (Socket socket = servSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {

                // Получить индекс числа Фибоначчи с полученым от клиента индексом
                out.println("Число Фибоначчи с индексом "
                        + line + " равно " + fib(Long.parseLong(line)));

                // Выход если от клиента получили end
                if (line.equals("end")) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static long fib(long index) {

        if (index == 0) // простой случай
            return 0;
        else if (index == 1) // простой случай
            return 1;
        else { //  упрощение и рекурсивные вызовы
            return fib(index - 1) + fib(index - 2);
        }
    }
}

