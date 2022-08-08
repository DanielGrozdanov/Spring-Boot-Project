package online.store.onlineBookStore.utility.email;

import online.store.onlineBookStore.filepath.FilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OrderFileDetails {
    private String orderContent;

    public OrderFileDetails() {
        this.orderContent = "";
    }

    public String getOrderContent() {
        return this.orderContent;
    }

    public void getFile() throws FileNotFoundException {
        File file = new File(FilePath.FILE_PATH);
        Scanner content = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (content.hasNextLine()){
            String fileData = content.nextLine();
            builder.append(fileData);
        }
        content.close();
        this.orderContent = builder.toString().replaceAll("%", "\n");
    }
}
