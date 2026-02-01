package rocks.pillow.nixie;

import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("gg.essential.network.connectionmanager.Connection")
public class ConnectionTransformer extends MiniTransformer {

	@Patch.Method("<clinit>()V")
	public void patchClinit(PatchContext ctx) {
		PatchContext.SearchResult sr = ctx.search(LDC("wss://connect.essential.gg/v1"));
		sr.jumpAfter();

		// replace the string on the stack
		ctx.add(INVOKESTATIC(
				"rocks/pillow/nixie/ConnectionTransformer$Hooks",
				"rewriteDefaultUri",
				"(Ljava/lang/String;)Ljava/lang/String;"
		));
	}

	public static class Hooks {
		public static String rewriteDefaultUri(String original) {

			NixiePremain.log.info(String.format("Replacing instance %s with %s", original, NixiePremain.config.getUrl()));
			return NixiePremain.config.getUrl();
		}
	}


}
