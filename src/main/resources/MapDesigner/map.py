import pygame


class Tile:

    WIDTH = 32
    HEIGHT = 16

    ROW_COLORS = [pygame.image.load(
        "images/row_" + str(i) + ".png") for i in range(1, 17)]
    UNBREAKABLE = pygame.image.load("images/brick_unbreakable.png")
    HAS_POWERUP = pygame.image.load("images/powerup.png")
    HEALTH_IMAGES = [pygame.image.load(
        "images/health_" + str(i) + ".png") for i in range(1, 5)]

    def __init__(self, x, y) -> None:
        self.x = x
        self.y = y

        self.marked = False
        self.color = 0
        self.unbreakable = False
        self.powerup = False
        self.health = 0

    def getsurface(self) -> pygame.Surface:
        surface = pygame.Surface((Tile.WIDTH, Tile.HEIGHT))
        surface.fill((255, 255, 255))
        if self.marked and not self.unbreakable and self.color < len(Tile.ROW_COLORS):
            surface.blit(Tile.ROW_COLORS[self.color], (0, 0))
        if self.marked and self.unbreakable:
            surface.blit(Tile.UNBREAKABLE, (0, 0))
        if self.powerup:
            surface.blit(Tile.HAS_POWERUP, (16, 0))
        if self.health > 0:
            surface.blit(Tile.HEALTH_IMAGES[self.health - 1], (1, 1))

        return surface

    def setHealth(self, value):
        if not self.marked or self.unbreakable:
            return
        self.health = value

    def reset(self):
        self.marked = False
        self.unbreakable = False
        self.powerup = False
        self.health = 0
        self.color = 0

    def setColor(self, i):
        self.color = i
        self.marked = True
        self.unbreakable = False
        self.health = 1

    def setUnbreakable(self, value):
        self.unbreakable = value
        self.marked = value
        self.health = 1

    def setPowerup(self, value):
        if not self.marked or self.unbreakable:
            return
        self.powerup = value

    def getValue(self):
        if not self.marked:
            return 0 << 4 | 1 << 3 | 1 << 2 | 0
        value = ((self.color) << 4) | (self.unbreakable) << 3 | (
            self.powerup << 2) | (max(self.health - 1, 0))
        return int(value)


class Map:
    def __init__(self) -> None:
        self.data = []
        for i in range(18):
            self.data.append([])
            for j in range(20):
                self.data[i].append(Tile(i, j))
                self.data[i][j].marked = False  # bool(random.randint(0, 1))

    def getSurface(self) -> pygame.surface.Surface:
        surface = pygame.Surface(
            (len(self.data) * Tile.WIDTH + len(self.data) + 1, len(self.data[0]) * Tile.HEIGHT + len(self.data[0]) + 1))
        surface.fill((100, 100, 100))
        for x in range(len(self.data)):
            for y in range(len(self.data[0])):
                surface.blit(self.data[x][y].getsurface(
                ), (x * Tile.WIDTH + x + 1, y * Tile.HEIGHT + y + 1))

        return surface

    def toTextFile(self, filename: str):
        with open(filename, "w+") as file:
            for y in range(len(self.data[0])):
                for x in range(len(self.data)):
                    file.write(str(self.data[x][y].getValue()) + " ")
                file.write("\n")
