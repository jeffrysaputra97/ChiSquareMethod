/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chisquare_1115018_1115040;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Natalie & Jeffry
 */
public class ChiSquare_1115018_1115040 {

    /**
     * @param args the command line arguments
     */
    public static Scanner scan = new Scanner(System.in);
    static ArrayList<ArrayList<Integer>> list_pendidikan = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> list_gender = new ArrayList<>();
    static final double ALPHA = 0.05;
    
    static HashMap<String, Double> list_korelasi = new HashMap<String, Double>();
    
    public static double hitungPendidikan(int index)
    {
        int result=0;
        for (Integer count: list_pendidikan.get(index)) {
            
            result+= count;
        }
        return result;
    }
    
    public static double hitungGender(int index)
    {
        int result=0;
        for (ArrayList<Integer> count: list_pendidikan) {
            
            for(int i=0; i<count.size(); i++)
            {
                if(i==index)
                {
                    result += count.get(i);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<HashMap<Double, Double>> distribution_table = new ArrayList<>();
        HashMap<Double, Double> temp = new HashMap<>();
        temp.put(0.99, 0.000);
        temp.put(0.95, 0.004);
        temp.put(0.90, 0.016);
        temp.put(0.75, 0.102);
        temp.put(0.5, 0.455);
        temp.put(0.25, 1.32);
        temp.put(0.1, 2.71);
        temp.put(0.05, 3.84);
        temp.put(0.01, 6.63);
        distribution_table.add(temp);
        temp = new HashMap<>();
        temp.put(0.99, 0.02);
        temp.put(0.95, 0.103);
        temp.put(0.90, 0.211);
        temp.put(0.75, 0.575);
        temp.put(0.5, 1.386);
        temp.put(0.25, 2.77);
        temp.put(0.1, 4.61);
        temp.put(0.05, 5.99);
        temp.put(0.01, 9.21);
        distribution_table.add(temp);
        
        int priasmp=0, priasma=0, priasarjana=0;
        int wanitasmp=0, wanitasma=0, wanitasarjana=0;
        System.out.print("Input jumlah pria SMP: ");
        priasmp = scan.nextInt();
        System.out.print("Input jumlah pria SMA: ");
        priasma = scan.nextInt();
        System.out.print("Input jumlah pria Sarjana: ");
        priasarjana = scan.nextInt();
        
        System.out.print("Input jumlah wanita SMP: ");
        wanitasmp = scan.nextInt();
        System.out.print("Input jumlah wanita SMA: ");
        wanitasma = scan.nextInt();
        System.out.print("Input jumlah wanita Sarjana: ");
        wanitasarjana = scan.nextInt();
        
        list_gender.add(priasmp);
        list_gender.add(wanitasmp);
        list_pendidikan.add(list_gender);
         
        list_gender = new ArrayList<>();
        list_gender.add(priasma);
        list_gender.add(wanitasma);
        list_pendidikan.add(list_gender);
        
        list_gender = new ArrayList<>();
        list_gender.add(priasarjana);
        list_gender.add(wanitasarjana);
        list_pendidikan.add(list_gender);

        list_korelasi.put("smp", hitungPendidikan(0));
        list_korelasi.put("sma", hitungPendidikan(1));
        list_korelasi.put("sarjana", hitungPendidikan(2));
        list_korelasi.put("pria", hitungGender(0));
        list_korelasi.put("wanita", hitungGender(1));
        int dof = (list_pendidikan.size() - 1) * (list_gender.size() - 1);
        
        double total = list_korelasi.get("pria")+list_korelasi.get("wanita");
        
        double x = 0;
        for(int i=0; i<list_pendidikan.size(); i++)
        {
            for(int j=0; j<list_gender.size(); j++)
            {
                int dataO = list_pendidikan.get(i).get(j);
                double dataE = hitungPendidikan(i) * hitungGender(j) / total;
                x += Math.pow(dataO - dataE, 2) / dataE;
            }
        }
        
        System.out.println("");
        System.out.println("Nilai chi square : " + x);
        
        if (x < distribution_table.get(dof - 1).get(ALPHA)) {
            System.out.println("Tidak perlu mencari nilai korelasi");
        } else {
            double print = Math.sqrt(x / (x + total)); 
            System.out.println("Nilai korelasi antar variabel: " + print);
        }
    }
    
    
    
    
}
