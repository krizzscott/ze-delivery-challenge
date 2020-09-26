package com.krizzscott.zedeliverychallenge.fixtures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.krizzscott.zedeliverychallenge.domains.GeometryType;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.CoverageAreaDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.GeoAddressDTO;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.request.PartnerRequestDTO;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PartnerRequestDTOTemplate implements TemplateLoader {
	@Override
	public void load() {
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("valid", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("valid-with-document-random", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", cnpj());
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("valid-with-real-coordinates", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", cnpj());
				add("address", one(GeoAddressDTO.class, "valid-real-coordinates"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid-real-coordinates"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-tradingName-null", new Rule() {
			{
				add("tradingName", null);
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-tradingName-empty", new Rule() {
			{
				add("tradingName", "");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-ownerName-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", null);
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-ownerName-empty", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-document-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", null);
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-document-empty", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-document-invalid_pattern", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "6717076200/107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-document-invalid_size", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "671707620000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-address-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", null);
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-address_type-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "with-type-null"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-address_type-diff-to-point", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "with-type-diff-to-point"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-address_coordinates-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "with-coordinates-null"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-address_coordinates-invalid_size", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "with-coordinates-invalid_size"));
				add("coverageArea", one(CoverageAreaDTO.class, "valid"));
			}
		});
		
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-coveragearea_type-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "with-type-null"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-coveragearea_type-diff-to-multipolygon", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "with-type-diff-to-multipolygon"));
			}
		});
		Fixture.of(PartnerRequestDTO.class).addTemplate("with-coveragearea_coordinates-null", new Rule() {
			{
				add("tradingName", "Nome da Adega");
				add("ownerName", "Nome do Dono");
				add("document", "67170762000107");
				add("address", one(GeoAddressDTO.class, "valid"));
				add("coverageArea", one(CoverageAreaDTO.class, "with-coordinates-null"));
			}
		});

		Fixture.of(GeoAddressDTO.class).addTemplate("valid", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", Arrays.asList(1d,2d));
			}
		});
		
		Fixture.of(GeoAddressDTO.class).addTemplate("valid-real-coordinates", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", Arrays.asList(-46.86915636062622, -23.50679863566516));
			}
		});
		
		Fixture.of(GeoAddressDTO.class).addTemplate("with-type-null", new Rule() {
			{
				add("type", null);
				add("coordinates", Arrays.asList(1d,2d));
			}
		});
		
		Fixture.of(GeoAddressDTO.class).addTemplate("with-type-diff-to-point", new Rule() {
			{
				add("type", "Point2");
				add("coordinates", Arrays.asList(1d,2d));
			}
		});
		
		Fixture.of(GeoAddressDTO.class).addTemplate("with-coordinates-null", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", null);
			}
		});
		Fixture.of(GeoAddressDTO.class).addTemplate("with-coordinates-invalid_size", new Rule() {
			{
				add("type", GeometryType.POINT.toString());
				add("coordinates", Arrays.asList(1d));
			}
		});
		
		Fixture.of(CoverageAreaDTO.class).addTemplate("valid", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", generateMultiPolygon());
			}
		});
		Fixture.of(CoverageAreaDTO.class).addTemplate("valid-real-coordinates", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", generateMultiPolygonWithRealCoordinates());
			}


		});
		Fixture.of(CoverageAreaDTO.class).addTemplate("with-type-null", new Rule() {
			{
				add("type", null);
				add("coordinates", generateMultiPolygon());
			}
		});
		Fixture.of(CoverageAreaDTO.class).addTemplate("with-type-diff-to-multipolygon", new Rule() {
			{
				add("type", null);
				add("coordinates", generateMultiPolygon());
			}
		});
		Fixture.of(CoverageAreaDTO.class).addTemplate("with-coordinates-null", new Rule() {
			{
				add("type", GeometryType.MULTIPOLYGON.toString());
				add("coordinates", null);
			}
		});
	}
	
	private List<List<List<List<Double>>>> generateMultiPolygonWithRealCoordinates() {
		List<List<List<List<Double>>>> multipolygon = new ArrayList<List<List<List<Double>>>>();
		multipolygon.add(Arrays.asList(Arrays.asList(
				Arrays.asList(-46.866538524627686, -23.510143676218334),
				Arrays.asList(-46.866044998168945, -23.509395968643272),
				Arrays.asList(-46.86381340026855, -23.508648256823566),
				Arrays.asList(-46.864285469055176, -23.5072708818342),
				Arrays.asList(-46.86625957489014, -23.505362209828007),
				Arrays.asList(-46.86896324157715, -23.505342532652993),
				Arrays.asList(-46.87035799026489, -23.506247679661502),
				Arrays.asList(-46.870315074920654, -23.50793989434221),
				Arrays.asList(-46.866538524627686, -23.510143676218334)
				)));
		return multipolygon;
	}
	
	
	private List<List<List<List<Double>>>> generateMultiPolygon() {
		List<List<List<List<Double>>>> multipolygon = new ArrayList<List<List<List<Double>>>>();
		multipolygon.add(new ArrayList<List<List<Double>>>());
		multipolygon.get(0).add(new ArrayList<List<Double>>());
		multipolygon.get(0).get(0).add(Arrays.asList(Double.valueOf(4d), Double.valueOf(4d)));
		multipolygon.get(0).get(0).add(Arrays.asList(Double.valueOf(2d), Double.valueOf(3d)));
		multipolygon.get(0).get(0).add(Arrays.asList(Double.valueOf(3d), Double.valueOf(2d)));
		multipolygon.get(0).get(0).add(Arrays.asList(Double.valueOf(4d), Double.valueOf(4d)));
		
		multipolygon.add(new ArrayList<List<List<Double>>>());
		multipolygon.get(1).add(new ArrayList<List<Double>>());
		multipolygon.get(1).get(0).add(Arrays.asList(Double.valueOf(1d), Double.valueOf(1d)));
		multipolygon.get(1).get(0).add(Arrays.asList(Double.valueOf(2d), Double.valueOf(4d)));
		multipolygon.get(1).get(0).add(Arrays.asList(Double.valueOf(4d), Double.valueOf(2d)));
		multipolygon.get(1).get(0).add(Arrays.asList(Double.valueOf(1d), Double.valueOf(1d)));
		return multipolygon;
	}

}
