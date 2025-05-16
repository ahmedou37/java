

public class Maain {
    public static void main(String[] args) {
        TurnLock lock= new TurnLock();
        TicTac game1 = new TicTac();
        TicTac game2 = new TicTac();

        game1.remplir();
        game2.remplir();

        Thread customer1 = new Thread(() -> {
            try {
                game1.ask(); 
                while (true) {
                    synchronized (lock) {
                        while (!lock.isCustomer1Turn) {
                            lock.wait();
                        }

                        game1.position1(); 
                        if (game1.fin()) {
                            game1.decision();
                            break;
                        }

                        lock.isCustomer1Turn = false;
                        lock.notifyAll();
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread customer2 = new Thread(() -> {
            try {
                game2.ask();  
                while (true) {
                    synchronized (lock) {
                        while (lock.isCustomer1Turn) {
                            lock.wait();
                        }

                        game2.position2();  // customer2 plays
                        if (game2.fin()) {
                            game2.decision();
                            break;
                        }

                        lock.isCustomer1Turn = true;
                        lock.notifyAll();
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        customer1.start();
        customer2.start();
    }
}
