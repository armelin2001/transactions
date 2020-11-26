package exercicios;

import model.CommodityAvr;
import model.CommodityAvr4;
import model.CommodityModel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Exercicio4 {
    public static void main(String args[]) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("exercicio4").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddTransaction = sc.textFile("in/transactions.csv");
        JavaRDD<String> rddSemCabecalho = rddTransaction.filter(x -> !x.startsWith("country_or_area") && !x.endsWith("category"));
        JavaPairRDD<Integer, CommodityAvr4> prdd = rddSemCabecalho.mapToPair(l->{
            String[] colunas = l.split(";");
            int ano = Integer.parseInt(colunas[1]);
            //long quantity;
            float price = Float.parseFloat(colunas[5]);
            String nameComm = colunas[3];
            /*if (colunas[8].isEmpty()){
                quantity = 0;
            }
            else {
                quantity = Long.parseLong(colunas[8]);
            }*/
            return new Tuple2<>(ano,new CommodityAvr4(price,1));
            });
        JavaPairRDD<Integer,CommodityAvr4> somado = prdd.reduceByKey((x,y)->
                new CommodityAvr4(x.getPrice()+y.getPrice(),x.getQuantity()+y.getQuantity()));

        JavaPairRDD<Integer,Float> media = somado.mapValues(x->x.getPrice()/x.getQuantity());

        JavaPairRDD<Integer,Float> ordena = media.sortByKey(true);

        ordena.coalesce(1).saveAsTextFile("output/Exercicio4");
    }
}
//country_or_area0 ;year1 ;comm_code2;commodity3;flow4;trade_usd5;weight_kg6;quantity_name7;quantity8;category
