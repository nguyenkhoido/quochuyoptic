package com.vn.quochuyapplication

/*
class CompanyBK: BaseActivity<ActivityCompanyBinding, CompanyViewModel> implements ProductAdapter.ItemProductClick {

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            Bundle extras = intent.getExtras();
            String companyName = extras != null ? extras.getString(AppConstants.COMPANY_NAME) : null;
            ((ActivityCompanyBinding) getViewDataBinding()).floatAddProduct.setOnClickListener(new CompanyActivity$onCreate$1(this, companyName));
            DataManager dataManager = ((CompanyViewModel) getMViewModel()).getDataManager();
            Intrinsics.checkNotNull(companyName);
            List<Frame> frameList = dataManager.getFrameList(companyName);
            List<Glasses> glassesList = ((CompanyViewModel) getMViewModel()).getDataManager().getGlassesList(companyName);
            List<Lense> lenseList = ((CompanyViewModel) getMViewModel()).getDataManager().getLenseList(companyName);
            List<Other> otherProductList = ((CompanyViewModel) getMViewModel()).getDataManager().getOtherProductList(companyName);
            List productList = new ArrayList();
            productList.addAll(frameList);
            productList.addAll(glassesList);
            productList.addAll(lenseList);
            productList.addAll(otherProductList);
            ProductAdapter it = new ProductAdapter(this, productList, this);
            LinearLayoutManager lnManager = new LinearLayoutManager(this, 1, false);
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C1879R.C1882id.list_product);
            Intrinsics.checkNotNullExpressionValue(recyclerView, "list_product");
            recyclerView.setLayoutManager(lnManager);
            RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C1879R.C1882id.list_product);
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "list_product");
            recyclerView2.setAdapter(it);
            DebugLog debugLog = DebugLog.INSTANCE;
            debugLog.mo26155e("NGUYENDK " + ((CompanyViewModel) getMViewModel()).getDataManager().getFrameList(companyName));
        }

        public void onItemClick(IProduct item) {
            Toast.makeText(this, item != null ? item.companyName() : null, 0).show();
            DialogAddProduct.Companion.newInstance(item).showNow(getSupportFragmentManager(), DialogAddProduct.class.getSimpleName());
        }

        public void onItemLongClick(IProduct item) {
            Toast.makeText(this, item != null ? item.productCode() : null, 0).show();
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this).setMessage((CharSequence) "Bạn muốn xoá sản phẩm này?").setPositiveButton((CharSequence) "Xoá", (DialogInterface.OnClickListener) CompanyActivity$onItemLongClick$builder$1.INSTANCE).setNegativeButton((CharSequence) "Huỷ", (DialogInterface.OnClickListener) CompanyActivity$onItemLongClick$builder$2.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(builder, "MaterialAlertDialogBuild…g.dismiss()\n            }");
            builder.show();
        }
    }

}*/
