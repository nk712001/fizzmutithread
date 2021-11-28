package edyoda6;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
        private int n;
        Semaphore f,b,fb,N;
        boolean done;

        public FizzBuzz(int n) {
                this.n = n;
                this.done = false;
                this.f = new Semaphore(0);
                this.b = new Semaphore(0);
                this.fb = new Semaphore(0);
                this.N = new Semaphore(1);

        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
                while(true){
                        f.acquire();
                        if(done == true) break;
                        printFizz.run();
                        N.release();
                }


        }

        public void buzz(Runnable printBuzz) throws InterruptedException {
                while(true){
                        b.acquire();
                        if(done == true) break;
                        printBuzz.run();
                        N.release();
                }
        }

        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
                while(true){
                        fb.acquire();
                        if(done == true) break;
                        printFizzBuzz.run();
                        N.release();
                }

        }
        public void number(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i < n+1; i++ ){
                        N.acquire();
                        if(i % 15 == 0){
                                fb.release();
                        }else if (i % 5 == 0){
                                b.release();
                        }else if(i % 3 == 0){
                                f.release();
                        }else{
                                printNumber.accept(i);
                                N.release();
                        }
                }

                N.acquire();
                done = true;
                f.release();
                b.release();
                fb.release();

        }
}

