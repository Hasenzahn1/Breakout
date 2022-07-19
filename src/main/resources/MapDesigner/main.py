import sys

import pygame

from map import Tile, Map
from ui import Button

# Setup pygame and create a wndow of size 600 x 800
pygame.init()
screen = pygame.display.set_mode((640, 800))
pygame.display.set_caption('Map Editor')
clock = pygame.time.Clock()


map = Map()
buttons = []

for i in range(len(Tile.ROW_COLORS)):
    buttons.append(Button(20 + i * Tile.ROW_COLORS[0].get_width(
    ) + i, 600, Tile.ROW_COLORS[i], i, lambda tile, button: tile.setColor(button.data)))

buttons.append(Button(20, 600 + Tile.ROW_COLORS[0].get_height(
) + 1, Tile.UNBREAKABLE, None, lambda tile, __: tile.setUnbreakable(True)))
buttons.append(Button(20 + Tile.ROW_COLORS[0].get_width() + 1, 600 + Tile.ROW_COLORS[0].get_height(
) + 1, Tile.HAS_POWERUP, None, lambda tile, __: tile.setPowerup(True)))

for i in range(len(Tile.HEALTH_IMAGES)):
    buttons.append(Button(20 + i * Tile.HEALTH_IMAGES[0].get_width() + 1, 600 + 2 * Tile.ROW_COLORS[0].get_height(
    ), Tile.HEALTH_IMAGES[i], i + 1, lambda tile, button: tile.setHealth(button.data)))

font = pygame.font.SysFont("Arial", 30)
s = font.render("Save", True, (0, 0, 0))

saveButton = Button(20, 700, s, None, None)


currentButton = None


def getbinary(x, n): return format(x, "b").zfill(n)


# Loop until the user clicks the close button.
pressed = False
lastPos = ()
mouseBtn = 0
running = True
i = 1
while running:

    surface = map.getSurface()
    x, y = (screen.get_width() - surface.get_width()) / 2, 40

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            done = False
            pygame.quit()
            sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            pressed = True
            mouseBtn = event.button

        if event.type == pygame.MOUSEBUTTONUP:
            pressed = False
            lastPos = ()

            if saveButton.processClick(event.pos[0], event.pos[1]):
                map.toTextFile("export.blf")
                print("Saved")
                continue

            for b in buttons:
                if b.processClick(event.pos[0], event.pos[1]):
                    if currentButton != None:
                        currentButton.reset()
                        if currentButton == b:
                            currentButton = None
                            break

                    currentButton = b
                    currentButton.selected = True
                    break

    if pressed:
        px, py = pygame.mouse.get_pos()[0] - x, pygame.mouse.get_pos()[1] - y
        if px < surface.get_width() and py < surface.get_height():
            tx, ty = int(px // (Tile.WIDTH + 1)), int(py // (Tile.HEIGHT + 1))
            if (tx, ty) != lastPos and tx >= 0 and ty >= 0 and tx < len(map.data) and ty < len(map.data[0]):
                if mouseBtn == 1:
                    if currentButton == None:
                        map.data[tx][ty].reset()
                    else:
                        currentButton.function(map.data[tx][ty], currentButton)
                else:
                    map.data[tx][ty].reset()

                lastPos = (tx, ty)

    screen.fill((255, 255, 255))

    for b in buttons:
        screen.blit(b.getsurface(), (b.x, b.y))

    screen.blit(saveButton.getsurface(), (saveButton.x, saveButton.y))

    screen.blit(map.getSurface(), (x, y))

    pygame.display.flip()
    clock.tick(30)
