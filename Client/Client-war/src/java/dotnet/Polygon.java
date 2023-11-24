package dotnet;

import java.util.List;

public class Polygon {
    private List<Borne> bornes; // Utilisez un nom de variable en français "bornes" au lieu de "borne".

    public Polygon(List<Borne> bornes) {
        this.bornes = bornes;
    }

    public double calculPerimetre() {
        double perimeter = 0;
        int numPoints = bornes.size();

        for (int i = 0; i < numPoints; i++) {
            Borne currentPoint = bornes.get(i);
            Borne nextPoint = bornes.get((i + 1) % numPoints);

            double lat1 = Math.toRadians(currentPoint.getLatitude());
            double lon1 = Math.toRadians(currentPoint.getLongitude());
            double lat2 = Math.toRadians(nextPoint.getLatitude());
            double lon2 = Math.toRadians(nextPoint.getLongitude());

            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = 6371 * c; // Utilisez directement 6371 pour le rayon de la Terre.

            perimeter += distance;
        }

        return perimeter;
    }

    public double calculSuperficie() {
        double area = 0;
        int numPoints = bornes.size();

        for (int i = 0; i < numPoints; i++) {
            Borne currentPoint = bornes.get(i);
            Borne nextPoint = bornes.get((i + 1) % numPoints);

            double lat1 = Math.toRadians(currentPoint.getLatitude());
            double lon1 = Math.toRadians(currentPoint.getLongitude());
            double lat2 = Math.toRadians(nextPoint.getLatitude());
            double lon2 = Math.toRadians(nextPoint.getLongitude());

            area += (lon2 - lon1) * (2 + Math.sin(lat1) + Math.sin(lat2));
        }

        area = normalizeLongitude(area);
        area = Math.abs(area);
        return area * 6371 * 6371 / 2; // Utilisez directement 6371 pour le rayon de la Terre.
    }

    private double normalizeLongitude(double lon) {
        if (lon > Math.PI) {
            lon -= 2 * Math.PI;
        } else if (lon < -Math.PI) {
            lon += 2 * Math.PI;
        }
        return lon;
    }
}
