package com.krizzscott.zedeliverychallenge.fixtures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;
import com.krizzscott.zedeliverychallenge.domains.GeoPoint;
import com.krizzscott.zedeliverychallenge.domains.GeometryType;
import com.krizzscott.zedeliverychallenge.domains.Partner;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.function.Function;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PartnerTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Partner.class).addTemplate("create-partner-invalid_tradingname", new Rule() {
			{
				add("tradingName", null);
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-invalid_ownername", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", null);
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-invalid_document", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", null);
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-null_address", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", null);
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-null_coveragearea", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", null);
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-null_coordinates_address", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "invalid-null-coordinates"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-empty_coordinates_address", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "invalid-empty-coordinates"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-null_coordinates_coveragearea", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "invalid-null-coordinates"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-empty_coordinates_coveragearea", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "invalid-empty-coordinates"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-valid", new Rule() {
			{
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});
		Fixture.of(Partner.class).addTemplate("create-partner-valid-with-id", new Rule() {
			{
				add("id", "8570bb4a-6363-47fc-a3c6-75aa27a20fb0");
				add("tradingName", randomTradingName());
				add("ownerName", randomOwnerName());
				add("document", cnpj());
				add("address", one(GeoPoint.class, "valid"));
				add("coverageArea", one(GeoMultiPolygon.class, "valid"));
			}
		});

		Fixture.of(GeoPoint.class).addTemplate("valid", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", Arrays.asList(1d,2d));
			}
		});
		
		Fixture.of(GeoPoint.class).addTemplate("invalid-null-coordinates", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", null);
			}
		});
		
		Fixture.of(GeoPoint.class).addTemplate("invalid-empty-coordinates", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", Arrays.asList());
			}
		});

		Fixture.of(GeoMultiPolygon.class).addTemplate("valid", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", generateMultiPolygon());
			}
		});
		
		Fixture.of(GeoMultiPolygon.class).addTemplate("invalid-null-coordinates", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", null);
			}
		});
		Fixture.of(GeoMultiPolygon.class).addTemplate("invalid-empty-coordinates", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", new ArrayList<List<List<List<Double>>>>());
			}
		});
	}

	private Function randomTradingName() {
		return new Rule().random("Ze Dev do RS", "Ze Dev do RJ", "Ze Dev de SP", "Ze Dev de Barueri");
	}

	private Function randomOwnerName() {
		return new Rule().random("Ronaldinho Gaucho", "Ronaldo", "Romario", "AdultoNey", "RCMito Ceni");
	}

	private List<List<List<List<Double>>>> generateMultiPolygon() {
		List<List<List<List<Double>>>> multipolygon = 
				Arrays.asList(Arrays.asList(Arrays.asList(Arrays.asList(1d, 2d), Arrays.asList(1d, 2d),
						Arrays.asList(1d, 2d), Arrays.asList(1d, 2d), Arrays.asList(1d, 2d))));
		return multipolygon;
	}

}
