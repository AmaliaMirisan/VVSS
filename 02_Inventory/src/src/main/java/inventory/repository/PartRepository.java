package inventory.repository;

import inventory.exceptions.PartException;
import inventory.model.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PartRepository {
    private String filename;
    private List<Part> partList;
    public PartRepository(String filename) {
        this.filename = filename;

        if (filename.isEmpty()) {
            this.filename = "data/parts.txt";
        }

        this.partList = new ArrayList<>();
        readParts();
    }

    private void readParts(){
        File file = new File(filename);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line=br.readLine())!=null){
                Part part=getPart(line);
                partList.add(part);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Part getPart(String line){
        Part item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");

        int id = Integer.parseInt((st.nextToken()));
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        int inStock = Integer.parseInt((st.nextToken()));
        int min = Integer.parseInt((st.nextToken()));
        int max = Integer.parseInt((st.nextToken()));


        item = new Part(id, name, price, inStock,min, max);
        return item;
    }

    public void add(Part part) throws PartException {
        partList.add(part);
    }

    public List<Part> getAll(){
        return partList;
    }
}
