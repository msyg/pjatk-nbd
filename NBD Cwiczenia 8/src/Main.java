import java.net.UnknownHostException;
import org.apache.log4j.BasicConfigurator;
import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;

public class Main {
	
	private static RiakCluster setUpCluster() throws UnknownHostException {
        
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("localhost")
                .withRemotePort(8087) //8087 Protocol Buffers port (HTTP port 8098)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();
        
        System.out.println("Cluster started, running nodes:" + cluster.getNodes());

        return cluster;
    }

	public static void main(String[] args) {
		
		//BasicConfigurator.configure();//log4j uzywany w RiakClient
		Movie movie = new Movie(1, "Psychoza", "Hitchcock", 1960);
		
		try {			
			System.out.println("Tworzenie obiektu klienta i clustra");
			RiakCluster cluster = setUpCluster();
			RiakClient client = new RiakClient(cluster);
			
			 Namespace bucketName = new Namespace("movies");
			 Location key = new Location(bucketName, movie.getDirector());
			 
			 StoreValue storeMovie = new StoreValue.Builder(movie)
					 .withLocation(key)
					 .build();
			 
			 //Wrzucanie nowego dokumentu do bazy
			 StoreValue.Response storeResp = client.execute(storeMovie);			 
			 System.out.println("Wrzucono nowy dokument: \n" + storeResp.getLocation());
			 
			 //Pobieranie dokumentu i wypisanie
			 FetchValue fetchOp = new FetchValue.Builder(key)
				        .build();
			 RiakObject fetchedMovie = client.execute(fetchOp).getValue(RiakObject.class);
			 System.out.println("Pobieranie i wypisywanie dokumentu: \n" + fetchedMovie);
			 
			 
			 //Modyfikowanie dokumentu
			 UpdateValue updateMovie = new UpdateValue.Builder(key)
				        .withFetchOption(FetchValue.Option.DELETED_VCLOCK, true)
				        .withUpdate(new UpdateMovie("Ptaki",1963))
				        .build();
			 UpdateValue.Response updateResp = client.execute(updateMovie);
			 System.out.println("Zmodyfikowano dokument: \n" + updateResp.getLocation());
				
			//Pobieranie i wypisanie zmodyfikowanego dokumentu
			
			RiakObject fetchedUpdatedMovie = client.execute(fetchOp).getValue(RiakObject.class);
			System.out.println("Pobieranie i wypisywanie zmodyfikowanego dokumentu: \n" + fetchedUpdatedMovie);
				
			//Usuwanie dokumentu
			DeleteValue delete = new DeleteValue.Builder(key).build();
			client.execute(delete);
			System.out.println("Usunieto dokument: \n" + delete.toString());
			
			//Pobieranie usunietego dokumentu
			RiakObject deletedMovie = client.execute(fetchOp).getValue(RiakObject.class);
			System.out.println("Pobieranie i wypisywanie zmodyfikowanego dokumentu: \n" + deletedMovie);
			 	 
	 	} 		
		
		catch (Exception e) {
         System.out.println(e.getMessage());
     }
		
	}

}
