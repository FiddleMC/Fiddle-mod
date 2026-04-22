package spout.client.fabric.ui.loadingoverlay;

import net.minecraft.resources.Identifier;
import spout.common.branding.SpoutNamespace;

/**
 * Holder for {@link #IDENTIFIER}.
 */
public final class LogoIdentifier {

    private LogoIdentifier() {
        throw new UnsupportedOperationException();
    }

    public static final Identifier IDENTIFIER = Identifier.fromNamespaceAndPath(SpoutNamespace.SPOUT, "icon.png");

}
