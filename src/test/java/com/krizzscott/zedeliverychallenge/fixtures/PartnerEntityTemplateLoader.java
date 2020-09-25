package com.krizzscott.zedeliverychallenge.fixtures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PartnerEntityTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(PartnerEntity.class).addTemplate("valid", new Rule() {
			{
				add("id", "8570bb4a-6363-47fc-a3c6-75aa27a20fb0");
				add("tradingName", "Ponto Maresias");
				add("ownerName", "Medina Zé");
				add("document", cnpj());
				add("address", new GeoJsonPoint(1d, 2d));
				add("coverageArea", generateMultiPolygon());
			}

		});

	}

	private GeoJsonMultiPolygon generateMultiPolygon() {
		List<GeoJsonPolygon> polygons = new ArrayList<GeoJsonPolygon>();
		List<Point> points = Arrays.asList(new Point(1d, 2d), new Point(2d, 2d), new Point(3d, 2d), new Point(1d, 2d));
		polygons.add(new GeoJsonPolygon(points));
		return new GeoJsonMultiPolygon(polygons);
	}

}
