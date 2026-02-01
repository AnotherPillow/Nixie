package rocks.pillow.nixie;

import nilloader.api.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

// All entrypoint classes must implement Runnable.
public class NixiePremain implements Runnable {

	// NilLoader comes with a logger abstraction that Does The Right Thing depending on the environment.
	// You should always use it.
	public static final NilLogger log = NilLogger.get("Nixie");
	public static final NixieConfig config = new NixieConfig();
	
	@Override
	public void run() {

		log.info("Hello from premain!");
		
		// You can change your desired mapping here. Setting it to "default" doesn't accomplish
		// anything, but it's here for illustration.
		ModRemapper.setTargetMapping("default");
		
		// Any class transformers need to be registered with NilLoader like this.
		ClassTransformer.register(new ConnectionTransformer());
	}

}
