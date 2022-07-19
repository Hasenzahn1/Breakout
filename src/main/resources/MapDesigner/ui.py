
import pygame


class Button:
    
    def __init__(self, x, y, image: pygame.Surface, data, function) -> None:
        self.x = x
        self.y = y
        self.image = image
        self.data = data
        
        self.selected = False
        self.function = function

    def processClick(self, x, y) -> bool:
        # print(x, y)
        if x < self.x or y < self.y: return False
        if x > self.x + self.image.get_width() or y > self.y + self.image.get_height(): return False
        return True
        
    def reset(self):
        self.selected = False

    def getsurface(self) -> pygame.Surface:
        surface = pygame.Surface((self.image.get_width(), self.image.get_height()))
        surface.fill((255, 255, 255))
        surface.blit(self.image, (0, 0))
        if self.selected:
            pygame.draw.line(surface, (0, 0, 0), (0, 0), (self.image.get_width(), 0), width = 2)
            pygame.draw.line(surface, (0, 0, 0), (self.image.get_width() - 2, 0), (self.image.get_width() - 2, self.image.get_height()), width = 2)
            pygame.draw.line(surface, (0, 0, 0), (0, 0), (0, self.image.get_height()), width = 2)
            pygame.draw.line(surface, (0, 0, 0), (0, self.image.get_height() - 2), (self.image.get_width(), self.image.get_height() - 2 ), width = 2)
        return surface

    
