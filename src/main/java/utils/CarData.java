/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *
 * @author mwn
 */
public class CarData implements Callable<String> {

    private int id;
    private String[] carCompanies = {"avis", "hertz", "europcar", "budget", "alamo"};
    private int week;
    private String address;

    public CarData(int id, int week, String address) {
        this.id = id;
        this.week = week;
        this.address = address;
    }

    @Override
    public String call() throws Exception {
        URL url = new URL("http://localhost:3333/availableCars?week=" + week + "&comp=" + carCompanies[id] + "&addr=" + address);
//        URL url = new URL("http://localhost:3333/availableCars?week=1&comp=europcar&addr=cph-airport");
        System.out.println(url.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()) {
            sb.append(scan.nextLine());
        }
        scan.close();
        System.out.println(sb.toString());
        return sb.toString();
    }

}