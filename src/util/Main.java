package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import model.map.Map;
import model.placement.Placement;
import model.transform.MapMapper;
import model.transform.PlacementMapper;
import model.untransformed.map.MapType;
import model.untransformed.placement.PlacementType;

public class Main {

	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	@SuppressWarnings("unused")
	public static void main(String[] args) throws JAXBException, FileNotFoundException, SAXException {

		Unmarshaller unmarshaller = createConfiguredMarshaller("model.untransformed.map", "xml/schema/Map.xsd");
		MapType untransformedMap = (MapType) unmarshaller
				.unmarshal(new FileInputStream(new File("xml/Map.xml")));
		MapMapper mapTransformer = new MapMapper();
		Map map = mapTransformer.mapType(untransformedMap);

		unmarshaller = createConfiguredMarshaller("model.untransformed.placement", "xml/schema/Placement.xsd");
		PlacementType untransformedPlacement = (PlacementType) unmarshaller
				.unmarshal(new FileInputStream(new File("xml/Placement.xml")));
		PlacementMapper placementTransformer = new PlacementMapper(mapTransformer);
		Placement placement = placementTransformer.mapType(untransformedPlacement);

		int i = 0;
	}

	public static Unmarshaller createConfiguredMarshaller(String contextPath, String schemaPath)
			throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance(contextPath);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new File(schemaPath));
		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new ValidationEventHandler() {

			@Override
			public boolean handleEvent(ValidationEvent event) {
				System.err.println("OH!");
				return false;
			}
		});

		return unmarshaller;
	}

}
