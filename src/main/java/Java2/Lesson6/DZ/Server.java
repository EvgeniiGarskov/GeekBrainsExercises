package Java2.Lesson6.DZ;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    /*
    1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может
    писать сообщения как на клиентской стороне, так и на серверной. Т.е. если на клиентской
    стороне написать «Привет», нажать Enter, то сообщение должно передаться на сервер и там
    © geekbrains.ru 8
    отпечататься в консоли. Если сделать то же самое на серверной стороне, сообщение,
    соответственно, передаётся клиенту и печатается у него в консоли. Есть одна особенность,
    которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
    Такую ситуацию необходимо корректно обработать.
     */

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(7633);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.println("Клиент подключился!");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner console = new Scanner(System.in);

//            DataInputStream in = new DataInputStream(socket.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
//                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.println("/end");
//                            out.writeUTF("/end");
                            break;
                        }
                        System.out.println("Client: " + str);
                    }
                }
            });
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите сообщение");
                        String str = console.nextLine();
//                        String str = console.readUTF();
                        System.out.println("Cообщение отправлено");
                        out.println(str);
//                        out.writeUTF(str);
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
