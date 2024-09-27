import processing.core.PApplet;

public class BeachTheme extends PApplet {

    float boatX1;
    float boatX2;
    float boatSpeed = 3;

    float cloud1X = 0;
    float cloud2X = 300;

    Snowflake[] snowflakes;
    int numSnowflakes = 300;

    Bird bird1;
    Bird bird2;
    Bird bird3;
    Bird bird4;

    float sunX;
    float sunSpeed = 2;
    int sunDelay = 145;
    int sunCounter = 90;

    float carX = -130;
    float carX1 = -200;
    float noiseOffset = 0.0f;

    int xs = -120, ys = 275;

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Beach Theme" }, new BeachTheme());
    }

    public void sunmoon() {

        if (xs < 500) {
            fill(145, 142, 141);
            ellipse(xs += 25, ys -= 10, 75, 75);
        } else if (xs < 650) {
            fill(255, 255, 0);
            ellipse(xs += 25, ys -= 5, 75, 75);
        } else if (xs < 700) {
            fill(255, 255, 0);
            ellipse(xs += 25, ys, 75, 75);
        } else if (xs < 1000) {
            fill(255, 255, 0);
            ellipse(xs += 25, ys += 5, 75, 75);
        } else if (xs < 1300) {
            fill(255, 255, 0);
            ellipse(xs += 25, ys += 10, 75, 75);
        } else {
            fill(145, 142, 141);
            ellipse(xs += 25, ys += 10, 75, 75);
        }
        if (xs >= 1450) {
            xs = -120;
            ys = 275;

            float sunSize = map(xs, 500, 1000, 50, 150);
            ellipse(xs += 25, ys += 10, sunSize, sunSize);

        }

    }

    public void pagi() {
        if (xs <= 300) {
            background(59, 0, 179);
        } else if (xs <= 500) {
            background(25, 64, 255);
        } else if (xs <= 800) {
            background(179, 255, 255);
        } else if (xs <= 1200) {
            background(255, 128, 0);
        } else {
            background(59, 0, 179);
        }

        noStroke();
        sunmoon();
        fill(9, 173, 214);
        rect(0, 355, 1400, 400);
        fill(0, 78, 97);
        rect(0, 355, 1400, 25);
    }

    @Override
    public void settings() {
        size(1366, 768);
        smooth();
    }

    @Override
    public void setup() {
        boatX1 = width / 4;
        boatX2 = width / 2;

        bird1 = new Bird(100, 100, 2);
        bird2 = new Bird(200, 150, 3);
        bird3 = new Bird(300, 120, 4);
        bird4 = new Bird(400, 180, 5);

        sunX = width / 20;

        snowflakes = new Snowflake[numSnowflakes];

        for (int i = 0; i < numSnowflakes; i++) {
            snowflakes[i] = new Snowflake(random(width), random(-height, 0));
        }
    }

    @Override
    public void draw() {
        background(0, 0, 205);

        pagi();
        delay(76);

        fill(255, 178, 102);
        stroke(127);
        rect(0, height * 2 / 3, width, height * 1 / 3);

        drawPager();

        drawgarispager();

        drawRoad();

        drawgarisjalan();

        drawOceanWave();

        drawCloud(cloud1X, 100);
        drawCloud(cloud2X, 150);

        cloud1X += 1;
        cloud2X += 1;

        if (cloud1X > width) {
            cloud1X = -200;
        }
        if (cloud2X > width) {
            cloud2X = -200;
        }

        fill(0, 102, 204);
        noStroke();
        rect(0, height / 3, width, height / 3);

        drawBoat(boatX1, height * 5 / 8);
        drawBoat(boatX2, height * 3 / 4);

        boatX1 += boatSpeed * 3;
        boatX2 += boatSpeed * 3;

        if (boatX1 > width + 100) {
            boatX1 = -100;
        }
        if (boatX2 > width + 100) {
            boatX2 = -100;
        }

        float distance1 = dist(boatX1, height * 5 / 8, mouseX, mouseY);
        float angle1 = atan2(mouseY - height * 8 / 8, mouseX - boatX1);

        if (distance1 > 5) {
            boatX1 += cos(angle1) * boatSpeed;
        }

        drawPalmTree(100, height * 5 / 6);
        drawPalmTree(500, height * 5 / 6);
        drawPalmTree(900, height * 5 / 6);
        drawPalmTree(1300, height * 5 / 6);

        drawStreetLight(200, height * 2 / 3);
        drawStreetLight(0, height * 2 / 3);
        drawStreetLight(400, height * 2 / 3);
        drawStreetLight(600, height * 2 / 3);
        drawStreetLight(800, height * 2 / 3);
        drawStreetLight(1000, height * 2 / 3);
        drawStreetLight(1200, height * 2 / 3);
        drawStreetLight(1350, height * 2 / 3);

        drawCar(carX, height * 12 / 13);

        carX += 2;

        if (carX > width) {
            carX = -80;
        }

        drawCar1(carX1, height * 12 / 13);

        carX1 -= 2;

        if (carX1 < -160) {
            carX1 = width;
        }

        bird1.update();
        bird1.display();

        bird2.update();
        bird2.display();

        bird3.update();
        bird3.display();

        bird4.update();
        bird4.display();

        for (int i = 0; i < numSnowflakes; i++) {
            snowflakes[i].fall();
            snowflakes[i].display();
        }

    }

    void drawCloud(float x, float y) {
        fill(255);
        noStroke();
        ellipse(x, y, 100, 70);
        ellipse(x + 40, y + 10, 100, 70);
        ellipse(x + 70, y, 100, 70);
    }

    class Snowflake {
        float x, y;
        float speed;

        Snowflake(float x, float y) {
            this.x = x;
            this.y = y;
            this.speed = random(1, 3);
        }

        void fall() {

            y += speed;

            if (y > height) {
                y = random(-height, 0);
                x = random(width);
            }
        }

        void display() {

            fill(255);
            noStroke();
            ellipse(x, y, 8, 8);
        }
    }

    class Bird {
        float x, y;
        float speed;

        Bird(float x, float y, float speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        void update() {
            x += speed;

            if (x > width + 50) {
                x = -50;
                y = random(50, height / 3);
            }
        }

        void display() {
            fill(255, 255, 255);
            triangle(x, y, x + 15, y - 10, x + 30, y);
            ellipse(x, y, 30, 20);
        }
    }

    void drawRoad() {
        fill(64);
        noStroke();
        rect(0, height * 2 / 3 + 140, width, 120);
    }

    void drawPager() {
        fill(160);
        noStroke();
        rect(0, height * 2 / 3 + 50, width, 50);
    }

    void drawgarisjalan() {
        fill(255);
        for (int i = 0; i < width; i += 60) {
            rect(i + 20, 710, 20, 10);
        }
    }

    void drawgarispager() {
        fill(255);
        noStroke();
        rect(0, height * 2 / 3 + 48, width, 5);
    }

    void drawPalmTree(float x, float y) {
        fill(139, 69, 19);
        rect(x - 5, y - 120, 10, 120);

        fill(34, 139, 34);
        ellipse(x, y - 130, 80, 80);
        ellipse(x + 20, y - 150, 80, 80);
        ellipse(x - 20, y - 150, 80, 80);

    }

    void drawCar(float x, float y) {
        fill(255, 0, 0);
        rect(x, y - 95, 160, 60);

        fill(0);
        ellipse(x + 40, y + -35, 40, 40);
        ellipse(x + 120, y + -35, 40, 40);

        fill(255, 255, 0);

        rect(x + 155, y - 80, 10, 10);

        fill(150, 200, 255);
        rect(x + 30, y - 110, 80, 50);
    }

    void drawCar1(float x, float y) {

        fill(255, 0, 0);
        rect(x, y - 50, 160, 80);

        fill(0);
        ellipse(x + 40, y + 35, 40, 40);
        fill(255);
        ellipse(x + 40, y + 35, 20, 20);
        fill(0);
        ellipse(x + 120, y + 35, 40, 40);
        fill(255);
        ellipse(x + 120, y + 35, 20, 20);

        fill(0);

        rect(x + 155, y + 15, 10, 10);

        fill(150, 200, 255);
        rect(x + 10, y - 38, 40, 30);
        fill(150, 200, 255);
        rect(x + 75, y - 38, 30, 30);
        fill(150, 200, 255);
        rect(x + 120, y - 38, 30, 30);
    }

    void drawStreetLight(float x, float y) {

        fill(100);
        rect(x, y - -30, 20, 100);

        fill(255, 255, 0);
        ellipse(x + 10, y - -20, 37, 37);
    }

    void drawBoat(float x, float y) {
        float boatHeight = 150;
        float sailHeight = 100;

        fill(210, 105, 30);
        quad(x - 50, y - boatHeight, x - 100, y - boatHeight - 50, x + 100, y - boatHeight - 50, x + 50,
                y - boatHeight);

        fill(255);
        triangle(x, y - boatHeight - sailHeight, x + 30, y - boatHeight - 50, x - 30, y - boatHeight - 50);

    }

    void drawOceanWave() {
        fill(0, 102, 204);
        noStroke();

        for (int x = 0; x < width; x += 5) {
            float yOffset = map(noise(noiseOffset), 0, 3, -14, 14);
            rect(x, height / 3 + yOffset, 5, height / 3);
            noiseOffset += 0.04;
        }
    }
}