
import Model.BspTree;
import Model.Colors;
import Model.Equation;
import Model.FileParser;
import Model.Heuristique;
import Model.Node;
import Model.Point;
import Model.Segment;
import Model.SideType;
import Model.Utilities;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author zakariae
 */
public class ClassTesting {

    private Equation equation;
    private Segment s1;
    private Segment s2;
    private Segment s3;
    private Segment s4;
    private Segment s5;
    private Segment s6;
    private ArrayList<Segment> listOfSegmentsToTest;

    @Test
    @Ignore
    public void testEqualityEquation() {
        Point p1 = new Point(-3, -9);
        Point p2 = new Point(4, -5);
        equation = new Equation(p1, p2);

        try {
            assertEquals(equation, new Equation(0.57142857142857, -1, -7.2857142857143));
        } catch (Exception ex) {

        }
    }

    @Test
    @Ignore
    public void testequalitysecondEquation() {
        Point p1 = new Point(-200, 0);
        Point p2 = new Point(-141.421356, -141.421356);
        equation = new Equation(p1, p2);
        System.out.println(equation.toString());

        try {
            assertEquals(equation, new Equation(-2.3898305084746, -1, -477.96610169492));
        } catch (Exception ex) {

        }
    }

    @Test
    @Ignore
    public void evaluatingPointSide() throws Exception {
        Point p1 = new Point(10, 1);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(5, 5);
        Segment segment = new Segment(p2, p3);
        assertEquals(segment.evalute(p1), SideType.Front);
        Point p4 = new Point(0, 0);
        assertEquals(segment.evalute(p4), SideType.OnLineSegment);
    }

    //was a test method before 
    // @Before
    @Test
    @Ignore
    public void evaluatingSegmentSide() throws Exception {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(5, 5);
        Segment segment = new Segment(p1, p2);
        Point p3 = new Point(1, 3);
        Point p4 = new Point(1, 5);
        Point p5 = new Point(3, 2);
        Point p6 = new Point(4, 2);
        s1 = new Segment(p3, p4);
        s2 = new Segment(p5, p6);
        assertEquals(segment.evalute(s1), SideType.Back);
        assertEquals(segment.evalute(s2), SideType.Front);

        Point p7 = new Point(4.5, 4);
        Point p8 = new Point(4.5, 7);
        s3 = new Segment(p7, p8);
        assertEquals(segment.evalute(s3), SideType.Intersect);

        Point p9 = new Point(4, 4);
        Point p10 = new Point(5, 5);
        s4 = new Segment(p9, p10);
        assertEquals(segment.evalute(s4), SideType.Collinear);

        Point p11 = new Point(4, 4);
        Point p12 = new Point(7, 2);
        s5 = new Segment(p12, p11);
        assertEquals(segment.evalute(s5), SideType.Front);

        Point p13 = new Point(0, 0);
        Point p14 = new Point(-1, 1);
        s6 = new Segment(p13, p14);
        assertEquals(segment.evalute(s6), SideType.Back);

        //another test -19881 -8258,37//we have to add a new epsilon value
        Segment orange = new Segment(new Point(-200.000000, 0.000000), new Point(-141.421356, -141.421356));
        System.out.println("the equation : " + orange.getEquation());
        Segment violet = new Segment(new Point(-141.421356, -141.421356), new Point(-0.000000, -200.000000));
        System.out.println("the violet is on the " + orange.evalute(violet) + " of the orange ");
        // assertEquals(orange.evalute(violet), SideType.Back);
    }

    @Test
    @Ignore
    public void intersectionPoint() throws Exception {

        Segment s1 = new Segment(new Point(0, 0), new Point(5, 5));
        Segment s2 = new Segment(new Point(2, 1), new Point(1, 2));
        Point p = s1.getIntersectionPoint(s2);
        assertEquals(p, new Point(1.5, 1.5));
        System.out.println(p);
        //deux segments orthogonaux
        Segment s3 = new Segment(new Point(2, 0), new Point(2, 4));
        Segment s4 = new Segment(new Point(0, 1), new Point(5, 1));
        Point p2 = s3.getIntersectionPoint(s4);
        assertEquals(p2, new Point(2, 1));
        System.out.println(p2);

    }

    //a test to spilt a segment on two new segments
    @Test
    @Ignore
    public void spiltSegment() throws Exception {

        Segment s1 = new Segment(new Point(0, 0), new Point(5, 5));
        Segment s2 = new Segment(new Point(1, 0), new Point(1, 2));
        Segment theBackSegment = new Segment();
        Segment theFrontSegment = new Segment();

        s1.spilt(s2, theBackSegment, theFrontSegment);
        //to be reviewed
        //System.out.println(theBackSegment);
        // System.out.println(theFrontSegment);

        Segment expectedSegment1 = new Segment(new Point(1, 1), new Point(1, 0));
        Segment expectedSegment2 = new Segment(new Point(1, 1), new Point(1, 2));

        assertEquals(expectedSegment1, theFrontSegment);
        assertEquals(expectedSegment2, theBackSegment);

        //another case
        Segment s3 = new Segment(new Point(0, 0), new Point(5, 5));
        Segment s4 = new Segment(new Point(6, 4), new Point(3, 7));
        Segment theBackSegment2 = new Segment();
        Segment theFrontSegment2 = new Segment();
        s3.spilt(s4, theBackSegment2, theFrontSegment2);
        //System.out.println(theBackSegment2);

    }

    @Test
    @Ignore
    public void getAndRemoveRandomSegmentFromAList() throws Exception {
        ArrayList<Segment> list = new ArrayList<Segment>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        for (Segment segment1 : list) {
            System.out.println(segment1);
        }
        //System.out.println("the first "+list.get(list.size()-1));
        int random = new Random().nextInt(list.size());
        System.out.println(random);
        Segment getRandomSegment = list.get(random);
        System.out.println(getRandomSegment);

        list.remove(random);
        System.out.println("after removing the " + random + " element");
        for (Segment segment1 : list) {
            System.out.println(segment1);
        }

    }

    //4 min for  ellipsesLarge.txt
    //5.9 pour randommedium
    //  pour randomLarge 10:40
    @Test  @Ignore
    public void testingThegenerationOfTheBinarySpaceTree() {
        System.out.println("the time before :" + new Date().toString());
        System.out.println("the listOfSegmentsSize is " + listOfSegmentsToTest.size());
        Node root = new Node();
        BspTree bspTree = new BspTree(root, Heuristique.H2);
        bspTree.buildBinarySpacePartitionTree(root, listOfSegmentsToTest);
        Segment segmentOntheRoot = (Segment) bspTree.getRoot().getListOfSegments().get(0);
        System.out.println("the root is " + segmentOntheRoot.getColor().getColorName());
        System.out.println("the height is " + bspTree.getHeight());

        System.out.println("the size of the tree is " + bspTree.getSize());
        System.out.println("the time after :" + new Date().toString());

    }

    @Before
    public void initialiosation() {
        listOfSegmentsToTest = new ArrayList<>();
        FileParser fileToParser = new FileParser();
        String theFilename = "/first/octangle.txt";//octogone
        // String theFilename = "/random/test.txt";
        System.out.println("here");
        String thePath = "/home/zakariae/Desktop/Scenes" + theFilename;
        try {
            File f = new File(thePath);
            System.out.println("the file " + f.getPath() + "" + f.isFile());
            listOfSegmentsToTest = fileToParser.parse(f);
        } catch (Exception ex) {
            System.out.println("we coudn't parse the file");
        }
        // System.out.println(listOfSegmentsToTest.size());

    }

    
    @Test
    public void testingH2(){
        
        
        int bestIndice = Utilities.getTheIndiceOfTheBestSegment(listOfSegmentsToTest);
        System.out.println("the best segment is "+listOfSegmentsToTest.get(bestIndice));
    }
    @Test
    @Ignore
    public void testingTheOctogone() {
        //System.out.println("affichage de la liste ");
        Segment jaune = listOfSegmentsToTest.get(6);
        Segment gris = listOfSegmentsToTest.get(2);
        System.out.println(jaune);
        System.out.println(gris.getP1() + "" + gris.getP2() + "" + gris.getEquation());
        System.out.println(jaune.evalute(gris));
        System.out.println(gris.evalute(jaune));
    }

    // error with the point location
    @Test
    @Ignore
    public void testingSide() {

        Point p = new Point(609.0, 388.0);
        //550.0 and y : 450.0 x :550.0 and y : 350.0 jaune
        //we took this segment Rose and the coordinate x :850.0 and y : 450.0 x :850.0 and y : 350.0       
        Segment jaune = new Segment(new Point(550, 450), new Point(550, 350), Colors.Jaune);
        Segment rose = new Segment(new Point(850, 450), new Point(850, 350), Colors.Rose);
        SideType s1 = jaune.evalute(p);
        SideType s2 = jaune.evalute(rose);
        System.out.println(s1 + " and " + s2);
    }

    @Test
    @Ignore
    public void testingCPointContainement() {
        Segment s = new Segment(new Point(0, 0), new Point(550, 0), Colors.Jaune);
        Point C = new Point(100, 0);
        Point X = new Point(600, 0);

        double p1 = Line2D.ptSegDist(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY(), C.getX(), C.getY());
        double p2 = Line2D.ptSegDist(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY(), X.getX(), X.getY());
        System.out.println("is contained " + p1);
        System.out.println("not contained " + p2);
    }

    @Test
    @Ignore
    public void TestingExceptionelCase() {

        Segment s1 = new Segment(new Point(0, 0), new Point(2, 2), Colors.Jaune);
        //  Segment s2 = new Segment(new Point(10, 10), new Point(0, 400), Colors.Jaune);
        Segment s3 = new Segment(new Point(1, 0), new Point(1, 1), Colors.Jaune);

        Polygon polygon = new Polygon();
        polygon.addPoint(0, 0);
        polygon.addPoint(2, 2);
        polygon.addPoint(4, 0);

        System.out.println("first point" + polygon.contains(s3.getP1()));
        System.out.println("second point" + polygon.contains(s3.getP2()));
        System.out.println("intersection point" + s1.getIntersectionPoint(s3));
        System.out.println("is there intersection point" + s1.evalute(s3));
        // System.out.println("if" + s1.evalute(s3));

//                if (firstAngleSegment.evalute(s).equals(SideType.Intersect) && inLineSegment(firstAngleSegment, intersfirstAngleSegment) == true) {
    }

    @Test
    @Ignore
    public void testingRectangle() {
        Segment s1 = new Segment(new Point(1, 2), new Point(2, 2), Colors.Jaune);
        Segment s3 = new Segment(new Point(3, 1), new Point(3, 3), Colors.Jaune);
        System.out.println(s1.evalute(s3));
        System.out.println("intersection point " + s1.getIntersectionPoint(s3));
        System.out.println(s1.getEquation().toString());
        System.out.println(s3.getEquation().toString());

        /*  Segment s2 = new Segment(new Point(1, 1), new Point(2, 2), Colors.Jaune);
        Segment s4 = new Segment(new Point(-1, 1), new Point(-2, 2), Colors.Jaune);
        System.out.println(s2.evalute(s4));
        System.out.println("intersection point " + s2.getIntersectionPoint(s4));*/
    }

    @Test
    @Ignore
    public void testingSpiltMethod() {
        Segment noir = new Segment(new Point(-300, -100), new Point(0, -100), Colors.Noir);
        Segment rouge = new Segment(new Point(0, -100), new Point(300, -100), Colors.Rouge);
        Segment jaune = new Segment(new Point(141.421356, -208.578644), new Point(0, -150.000000), Colors.Jaune);
        Segment frontSegment = new Segment();
        Segment backSegement = new Segment();
        jaune.spilt(noir, frontSegment, backSegement);

        System.out.println(jaune.evalute(rouge));
        //System.out.println("intersection point " + s1.getIntersectionPoint(s3));
        System.out.println("the front segment is "+jaune.evalute(frontSegment)+frontSegment);
        System.out.println("the back segment is "+jaune.evalute(backSegement)+backSegement);

        /*  Segment s2 = new Segment(new Point(1, 1), new Point(2, 2), Colors.Jaune);
        Segment s4 = new Segment(new Point(-1, 1), new Point(-2, 2), Colors.Jaune);
        System.out.println(s2.evalute(s4));
        System.out.println("intersection point " + s2.getIntersectionPoint(s4));*/
    }
}
