package ua.edu.sumdu;


import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {

        try {

            //String sprSht[][] = readSpreadsheetFromFile("src/main/resources/4x3.txt");
            String sprSht[][] = readSpreadsheetFromKeyboard();
            int n = sprSht.length;
            int m = sprSht[0].length;

            printSpreadSheet(new GetStringCellFromString(sprSht),n,m,20);

            int rowMiddle = n/2;
            int colMiddle = m/2;

            FutureTask<String> sprShtTasks[][] = new FutureTask[n][m];
            for (int iTop = 0, iBottom = n-1; n%2 == 0 ? (iTop < rowMiddle) : (iTop <=rowMiddle); iTop++, iBottom-- ) {
                for (int jTop = 0, jBottom = m-1; jTop < m; jTop++, jBottom-- ) {
                    if ( iTop == rowMiddle ) {
                        if ( m % 2 == 0 ) {
                            if ( jTop == colMiddle ) {
                                break;
                            }
                        } else {
                            if ( jTop == colMiddle ) {
                                createThread(sprShtTasks,sprSht,iTop,jTop).start();
                                break;
                            }
                        }
                    }
                    createThread(sprShtTasks,sprSht,iTop,jTop).start();
                    createThread(sprShtTasks,sprSht,iBottom,jBottom).start();
                }
            }

            printSpreadSheet(new GetStringCellFromTask(sprShtTasks),n,m,15);
        } catch (FileNotFoundException ex) {
            out.println(ex.getMessage());
        } catch (IOException ex) {
            out.println(ex.getMessage());
        }
    }

    public static String[][] readSpreadsheetFromKeyboard() throws FileNotFoundException,IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        String dimension = br.readLine();
        String countColsRows[] = dimension.trim().split(" ");
        int n = Integer.parseInt(countColsRows[0]);
        int m = Integer.parseInt(countColsRows[1]);
        String sprSht[][] = new String[n][m];
        int row = 0;
        String sprSheetRow;
        while( row != n ) {
            sprSht[row] = br.readLine().trim().split(" ");
            row++;
        }
        return sprSht;
    }

    public static String[][] readSpreadsheetFromFile(String filename) throws FileNotFoundException,IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String dimension = br.readLine();
        String countColsRows[] = dimension.trim().split(" ");
        int n = Integer.parseInt(countColsRows[0]);
        int m = Integer.parseInt(countColsRows[1]);
        //out.println(n + " " + m);
        String sprSht[][] = new String[n][m];
        int row = 0;
        String sprSheetRow;
        while( (sprSheetRow = br.readLine()) != null ) {
            sprSht[row] = sprSheetRow.trim().split(" ");
            row++;
        }
        return sprSht;
    }

    public static Thread createThread(FutureTask<String> sprShtTasks[][], String sprSht[][], int i, int j ) {
        CellHandler cellHandler = new CellHandler(sprSht, i, j);
        FutureTask<String> futureTask = new FutureTask(cellHandler);
        sprShtTasks[i][j] = futureTask;
        Thread thread = new Thread(futureTask);
        return thread;
    }

    public static void printSpreadSheet(Getter<String> getter, int n, int m, int cellWidth) {
        for (int i = 0; i < n; i++ ) {
            for (int j = 0; j < m; j++ ) {
                out.print(String.format("%-" + cellWidth + "s", getter.get(i,j) ) + " ");
            }
            out.println();
        }
    }

    public interface Getter<T> {
        public T get(int i, int j);
    }
    public static class GetStringCellFromTask implements Getter<String> {
        private FutureTask<String> futureTask[][];


        public GetStringCellFromTask(FutureTask<String> futureTask[][]) {
            this.futureTask = futureTask;
        }

        @Override
        public String get(int i, int j) {
            String result = null;
            try {
                result = futureTask[i][j].get();
            } catch (InterruptedException ex) {
                out.println("Поток прерван. " + ex.getMessage());
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
            return result;
        }
    }

    public static class GetStringCellFromString implements Getter<String> {
        private String array[][];


        public GetStringCellFromString(String array[][]) {
            this.array = array;
        }

        @Override
        public String get(int i, int j) {
            return array[i][j];
        }
    }
}
