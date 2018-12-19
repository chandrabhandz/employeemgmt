package db;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
/*import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import java.io.InputStream;*/
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class MongoDAO {
    private static final String SERVER_URL = "mongodb://localhost:27017";
    private static final String DATABASE = "employee_demo";
    private static final String COLLECTION = "employee";
    /*private static final String FSCOLLECTION = "";*/

    private MongoClient client;

    public void create(Document document) throws Exception {
        getCollection().insertOne(document);
    }

    public List<Document> read(Document filter) throws Exception {
        final List<Document> result = new ArrayList<Document>();
        FindIterable<Document> iterable = getCollection().find(filter);
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                result.add(document);
            }
        });
        return result;
    }

    public List<Document> readAll()throws Exception{
        final List<Document> result = new ArrayList<Document>();
        FindIterable<Document> iterable = getCollection().find();
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                result.add(document);
            }
        });
        return result;
    }

    /*public void update(Document filter, Document update) throws Exception {
        getCollection().updateMany(filter, update);
    }

    public void updateOne(Document filter, Document update) throws Exception {
        getCollection().updateOne(filter, update);
    }

    public void delete(Document filter) throws Exception {
        getCollection().deleteMany(filter);
    }

    public void deleteOne(Document filter) throws Exception {
        getCollection().deleteOne(filter);
    }

    public InputStream getStream(String id) throws Exception {
        @SuppressWarnings("deprecation")
        GridFS gfsPhoto = new GridFS(getClient().getDB(FSCOLLECTION));
        GridFSDBFile file = gfsPhoto.findOne(new ObjectId(id));
        if (file == null) {
            return null;
        } else {
            return file.getInputStream();
        }
    }*/

    private MongoCollection<Document> getCollection() throws Exception {
        MongoDatabase database = getDatabase();
        MongoCollection<Document> collection = database.getCollection(MongoDAO.COLLECTION);
        return collection;
    }

    private MongoDatabase getDatabase() throws Exception {
        MongoDatabase database = getClient().getDatabase(MongoDAO.DATABASE);
        return database;
    }

    private MongoClient getClient() throws Exception {
        if (client == null) {
            client = new MongoClient(new MongoClientURI(SERVER_URL));
        }
        return client;
    }
}
