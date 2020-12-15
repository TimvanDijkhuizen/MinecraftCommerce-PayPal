package nl.timvandijkhuizen.commerce.paypal;

import nl.timvandijkhuizen.commerce.CommerceApi;
import nl.timvandijkhuizen.spigotutils.PluginBase;

public class CommercePayPal extends PluginBase {

    @Override
    public void load() {
        CommerceApi.registerGatewayType(new GatewayPayPal());
    }

}
