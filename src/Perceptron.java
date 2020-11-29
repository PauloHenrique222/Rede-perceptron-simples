import java.util.Random;

public class Perceptron {

    public static void main(String args[]) {

        //Entradas
        int[][] x = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        //Saídas desejadas
        int[] d = {0, 1, 1, 1};
        //Pesos
        double[] w = new double[3];
        //Bias
        int b = 1;
        //Épocas
        int epocas = 10;
        //Taxa de aprendizagem
        double n = 0.7;
        //Inicializa a saídas como zeros
        int y[] = {0, 0, 0, 0};
        //Número de épocas necessária para encontrar o resultado
        int numEpocaResult;

        //Gerando os pesos aleatórios
        Random random = new Random();
        for (int i = 0; i < w.length; i++) {
            w[i] = random.nextDouble();
        }

        System.out.println("Entradas:");
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("----------------------------------------------------------------");

        System.out.println("Saídas desejadas:");
        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i]);
        }

        System.out.println("----------------------------------------------------------------");

        System.out.println("Bias: " + b);

        System.out.println("----------------------------------------------------------------");

        System.out.println("Pesos: ");
        for (int i = 0; i < w.length; i++) {
            System.out.print(w[i] + " ");
        }

        System.out.println("\n----------------------------------------------------------------");

        System.out.println("Quantidade de épocas usada para critério de parada: " + epocas);

        System.out.println("----------------------------------------------------------------");

        for (numEpocaResult = 0; numEpocaResult < epocas; numEpocaResult ++) {

            //inicializa o erro da época
            double erro_epoca = 0;

            for(int j = 0; j < x.length; j++){
                //Somatoria das entradas e pesos
                double u = (b * w[0]) + (x[j][0] * w[1]) + (x[j][1] * w[2]);

                /*Função de ativação
                    se u > 0, saída igual a 1
                    se u <= 0, saída igual a 0
                */
                if(u > 0){
                    y[j] = 1;
                }else{
                    y[j] = 0;
                }

                double delta = d[j] - y[j];

                w[0] = w[0] + n * delta;
                w[1] = w[1] + n * x[j][0] * delta;
                w[2] = w[2] + n * x[j][1] * delta;

                erro_epoca = erro_epoca + Math.abs(delta);

            }
            if(erro_epoca == 0){
                break;

            }
        }

       if(compararVetores(d, y)){
           System.out.println("Saídas da rede: ");
           for (int i = 0; i < y.length; i++) {
               System.out.println(y[i]);
           }

           System.out.println("----------------------------------------------------------------");

           System.out.println("Quantidade de épocas necessárias para chegar no resultado: " + (numEpocaResult + 1));
       }else {
           System.out.println("Não foi possível chegar ao um resultado desejado com os pesos, bias e número de épocas iniciados :(, tente novamente...");
       }
    }

    public static boolean compararVetores(int[] a, int[]b){
        for (int i = 0; i < a.length; i++) {
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }
}
