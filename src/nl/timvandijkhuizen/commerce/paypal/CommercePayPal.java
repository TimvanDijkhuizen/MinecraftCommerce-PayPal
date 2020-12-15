package nl.timvandijkhuizen.commerce.paypal;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import nl.timvandijkhuizen.commerce.events.RegisterGatewayTypesEvent;
import nl.timvandijkhuizen.spigotutils.PluginBase;
import nl.timvandijkhuizen.spigotutils.services.Service;

public class CommercePayPal extends PluginBase implements Listener {

    @Override
    public void init() throws Throwable {
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onGatewayRegister(RegisterGatewayTypesEvent event) {
        event.addType(new GatewayPayPal());
    }

    @Override
    public Service[] registerServices() throws Throwable {
        return new Service[] {};
    }

}
