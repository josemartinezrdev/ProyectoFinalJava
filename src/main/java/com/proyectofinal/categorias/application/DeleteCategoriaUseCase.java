package com.proyectofinal.categorias.application;

import com.proyectofinal.categorias.domain.service.CategoriaService;

public class DeleteCategoriaUseCase {
    private final CategoriaService categoriaService;

    public DeleteCategoriaUseCase(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void execute(int id) {
        categoriaService.deleteCategoria(id);
    }

}
