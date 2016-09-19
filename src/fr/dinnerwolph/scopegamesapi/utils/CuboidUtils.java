package fr.dinnerwolph.scopegamesapi.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class CuboidUtils {
    private static String worldname;
    private static Vector minimumPoint;
    private static Vector maximumPoint;
    private static Location minLoc;
    private static Location maxLoc;
    private static Map<Integer, Map<Location, Location>> loc;

    public CuboidUtils(Location minimum, Location maxmum, Integer nbr) {
        if (minimum == null || maxmum == null) {
            throw new NullArgumentException("location minimum || location maximum is null");
        }
        if (minimum.getWorld() != maxmum.getWorld()) {
            throw new IllegalStateException("world1 != world2");
        }
        worldname = minimum.getWorld().toString();
        minLoc = new Location(Bukkit.getWorld((String)"world"), Math.min(minimum.getX(), maxmum.getX()), Math.min(minimum.getY(), maxmum.getY()), Math.min(minimum.getZ(), maxmum.getZ()));
        maxLoc = new Location(Bukkit.getWorld((String)"world"), Math.max(minimum.getX(), maxmum.getX()), Math.max(minimum.getY(), maxmum.getY()), Math.max(minimum.getZ(), maxmum.getZ()));
        minimumPoint = new Vector(Math.min(minimum.getX(), maxmum.getX()), Math.min(minimum.getY(), maxmum.getY()), Math.min(minimum.getZ(), maxmum.getZ()));
        maximumPoint = new Vector(Math.max(minimum.getX(), maxmum.getX()), Math.max(minimum.getY(), maxmum.getY()), Math.max(minimum.getZ(), maxmum.getZ()));
        HashMap<Location, Location> map = new HashMap<Location, Location>();
        map.put(minLoc, maxLoc);
        loc.put(nbr, map);
    }

    public static boolean isOnArena(Location playerLocation, Integer nbr) {
        Map<Location, Location> map = loc.get(nbr);
        Map.Entry<Location, Location> mapp = map.entrySet().iterator().next();
        minLoc = mapp.getKey();
        maxLoc = mapp.getValue();
        minimumPoint = mapp.getKey().toVector();
        maximumPoint = mapp.getValue().toVector();
        if (playerLocation.getX() <= maxLoc.getX() && playerLocation.getX() >= minLoc.getX() && playerLocation.getY() <= maxLoc.getY() && playerLocation.getY() >= minLoc.getY() && playerLocation.getZ() <= maxLoc.getZ() && playerLocation.getZ() >= minLoc.getZ()) {
            return true;
        }
        return false;
    }

    static {
        loc = new HashMap<Integer, Map<Location, Location>>();
    }
}